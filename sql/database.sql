CREATE DATABASE  IF NOT EXISTS `delivery` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `delivery`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: delivery
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `id` int NOT NULL AUTO_INCREMENT,
  `department1_id` int NOT NULL,
  `department2_id` int NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  `state` varchar(255) NOT NULL,
  `size` int NOT NULL,
  `weight` float(7,2) NOT NULL,
  `receive_date` date NOT NULL,
  `baggage_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_application_department1_idx` (`department1_id`),
  KEY `fk_application_department2_idx` (`department2_id`),
  KEY `fk_application_user1_idx` (`user_id`),
  KEY `fk_application_application_state1_idx` (`state`),
  KEY `fk_application_baggage_type1_idx` (`baggage_type`),
  CONSTRAINT `fk_application_application_state1` FOREIGN KEY (`state`) REFERENCES `application_state` (`state`),
  CONSTRAINT `fk_application_baggage_type1` FOREIGN KEY (`baggage_type`) REFERENCES `baggage_type` (`type`),
  CONSTRAINT `fk_application_department1` FOREIGN KEY (`department1_id`) REFERENCES `department` (`id`),
  CONSTRAINT `fk_application_department2` FOREIGN KEY (`department2_id`) REFERENCES `department` (`id`),
  CONSTRAINT `fk_application_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `application_state`
--

DROP TABLE IF EXISTS `application_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application_state` (
  `state` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_state`
--

LOCK TABLES `application_state` WRITE;
/*!40000 ALTER TABLE `application_state` DISABLE KEYS */;
INSERT INTO `application_state` VALUES ('in processing',NULL),('sent',NULL),('waiting for payment',NULL);
/*!40000 ALTER TABLE `application_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baggage_type`
--

DROP TABLE IF EXISTS `baggage_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baggage_type` (
  `type` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baggage_type`
--

LOCK TABLES `baggage_type` WRITE;
/*!40000 ALTER TABLE `baggage_type` DISABLE KEYS */;
INSERT INTO `baggage_type` VALUES ('clothes','будь-який одяг'),('documents','паперові документи'),('fragile','кераміка, скло і т. д.'),('package','не попадає під інші категорії');
/*!40000 ALTER TABLE `baggage_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `region` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_city_region1_idx` (`region`),
  CONSTRAINT `fk_city_region1` FOREIGN KEY (`region`) REFERENCES `region` (`region`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Харків','Харківська'),(2,'Київ','Київська'),(4,'Маріуполь','Донецька'),(5,'Дергачі','Харківська');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `index` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `number` int DEFAULT NULL,
  `city_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_UNIQUE` (`index`),
  UNIQUE KEY `address_UNIQUE` (`address`),
  UNIQUE KEY `city_dept_num` (`number`,`city_id`),
  KEY `fk_department_city1_idx` (`city_id`),
  CONSTRAINT `fk_department_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'87549','просп. Металургів, 102',1,4),(2,'87548','просп. Миру, 108',2,4),(3,'87535','просп. Металургів, 235',3,4),(4,'87520','вул. Маміна-Сибіряка, 36',4,4),(5,'87521','вул. Городня, 160',5,4),(6,'87505','просп. Перемоги, 11',6,4),(7,'87512','вул. Варшавська, 106/32',7,4),(8,'61072','просп. Науки, 54',1,1),(9,'61108','вул. Академічна, 1',2,1),(10,'61145','вул. Новгородська, 2',3,1),(11,'03115','просп. Перемоги, 128/2',1,2),(12,'03028','вул. Велика Китаївська, 6',2,2),(13,'03087','вул. Єреванська, 9',3,2),(14,'62303','пл. Перемоги, 20',1,5),(15,'62302','вул. Сумський шлях, 178',2,5);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region` (
  `region` varchar(255) NOT NULL,
  PRIMARY KEY (`region`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES ('Донецька'),('Київська'),('Харківська');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('manager','опрацьовує заявки і формує квитанції до сплати, а також може отримувати звіти щодо доставок (по днях та напрямках)'),('user','може створити заявку на доставку вантажу і вказати адресу доставки');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tariff_distance`
--

DROP TABLE IF EXISTS `tariff_distance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tariff_distance` (
  `distance` varchar(255) NOT NULL,
  `cost` float(7,2) NOT NULL,
  PRIMARY KEY (`distance`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tariff_distance`
--

LOCK TABLES `tariff_distance` WRITE;
/*!40000 ALTER TABLE `tariff_distance` DISABLE KEYS */;
INSERT INTO `tariff_distance` VALUES ('city',10.00),('country',30.00),('region',20.00);
/*!40000 ALTER TABLE `tariff_distance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tariff_size`
--

DROP TABLE IF EXISTS `tariff_size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tariff_size` (
  `size` int NOT NULL,
  `cost` float(7,2) NOT NULL,
  PRIMARY KEY (`size`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tariff_size`
--

LOCK TABLES `tariff_size` WRITE;
/*!40000 ALTER TABLE `tariff_size` DISABLE KEYS */;
INSERT INTO `tariff_size` VALUES (1000,0.05),(2500,0.07),(1000000,0.09);
/*!40000 ALTER TABLE `tariff_size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tariff_weight`
--

DROP TABLE IF EXISTS `tariff_weight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tariff_weight` (
  `weight` float(7,2) NOT NULL,
  `cost` float(7,2) NOT NULL,
  PRIMARY KEY (`weight`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tariff_weight`
--

LOCK TABLES `tariff_weight` WRITE;
/*!40000 ALTER TABLE `tariff_weight` DISABLE KEYS */;
INSERT INTO `tariff_weight` VALUES (10.00,0.25),(50.00,0.50),(10000.00,1.00);
/*!40000 ALTER TABLE `tariff_weight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL DEFAULT 'user',
  `salt` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_user_role_idx` (`role`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role`) REFERENCES `role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `waybill`
--

DROP TABLE IF EXISTS `waybill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `waybill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  `application_id` int NOT NULL,
  `state` varchar(255) NOT NULL,
  `cost` float(7,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `application_id_UNIQUE` (`application_id`),
  KEY `fk_waybill_user1_idx` (`user_id`),
  KEY `fk_waybill_application1_idx` (`application_id`),
  KEY `fk_waybill_waybill_state1_idx` (`state`),
  CONSTRAINT `fk_waybill_application1` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`),
  CONSTRAINT `fk_waybill_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_waybill_waybill_state1` FOREIGN KEY (`state`) REFERENCES `waybill_state` (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waybill`
--

LOCK TABLES `waybill` WRITE;
/*!40000 ALTER TABLE `waybill` DISABLE KEYS */;
/*!40000 ALTER TABLE `waybill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `waybill_state`
--

DROP TABLE IF EXISTS `waybill_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `waybill_state` (
  `state` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waybill_state`
--

LOCK TABLES `waybill_state` WRITE;
/*!40000 ALTER TABLE `waybill_state` DISABLE KEYS */;
INSERT INTO `waybill_state` VALUES ('paid',NULL),('waiting for payment',NULL);
/*!40000 ALTER TABLE `waybill_state` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-24 23:51:01
