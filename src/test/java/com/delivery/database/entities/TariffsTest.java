package com.delivery.database.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TariffsTest {

    private Tariff tariff;

    @Before
    public void initTariff(){
        tariff = new Tariff();
    }

    @Test
    public void testConstCity(){
        assertEquals("city", Tariff.DISTANCE_CITY);
    }

    @Test
    public void testConstRegion(){
        assertEquals("region", Tariff.DISTANCE_REGION);
    }

    @Test
    public void testConstCountry(){
        assertEquals("country", Tariff.DISTANCE_COUNTRY);
    }

    @Test
    public void testDeliveryCost(){
        tariff.setSizeCost(10);
        tariff.setWeightCost(2);
        tariff.setDistanceCost(3);
        tariff.calculateCost();
        assertEquals(15, tariff.getDeliveryCost(), 0.0);
    }

    @Test
    public void testSetGetSize(){
        int size = 5;
        tariff.setSize(size);
        assertEquals(size, tariff.getSize());
    }

    @Test
    public void testSetGetWeight(){
        float weight = 5.5F;
        tariff.setWeight(weight);
        assertEquals(weight, tariff.getWeight(), 0.0);
    }

    @Test
    public void testSetGetDistance(){
        String distance = "city";
        tariff.setDistance(distance);
        assertEquals(distance, tariff.getDistance());
    }

}
