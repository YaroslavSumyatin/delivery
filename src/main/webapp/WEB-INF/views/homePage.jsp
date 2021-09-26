<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
<jsp:include page="_header.jsp"/>
<main class="container_main">
    This is demo Simple web application using jsp,servlet &amp; Jdbc. <br><br>
    This is demo Simple web application using jsp,servlet &amp; Jdbc. <br><br>
    This is demo Simple web application using jsp,servlet &amp; Jdbc. <br><br>
    This is demo Simple web application using jsp,servlet &amp; Jdbc. <br><br>
</main>
<jsp:include page="_footer.jsp"/>
</div>
</body>
</html>