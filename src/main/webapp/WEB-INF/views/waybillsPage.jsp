<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="com.delivery.database.entities.Waybill" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"></jsp:include>
    <main class="container_main">
        <div class="manage_waybills-content">
            <c:forEach items="${waybills}" var="w">
                <c:if test="${w.state eq Waybill.STATE_WAITING_FOR_PAYMENT}">
                    <div class="manage_waybills-item">
                        <c:forEach items="${users}" var="u">
                            <c:if test="${w.userId eq u.id}">
                                Manager who formed waybill: ${u.name.concat(" ").concat(u.surname)}<br>
                            </c:if>
                        </c:forEach>
                        <c:forEach items="${apps}" var="a">
                            <c:if test="${w.applicationId eq a.id}">
                                BaggageType: ${a.baggageType}<br>
                                State: ${a.state}<br>
                                Size: ${a.size}<br>
                                Weight: ${a.weight}<br>
                            </c:if>
                        </c:forEach>
                        Cost: ${w.cost} &nbsp;грн
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </main>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>