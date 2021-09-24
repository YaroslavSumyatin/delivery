<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tariffs Page</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"></jsp:include>
    <main class="container_main">
        <div class="tariff_table-container">
            <div class="tariff_table-container_inner">
            <div class="tariff_size-table">
                <table>
                    <tr>
                        <td>Size</td>
                        <td>Cost for 1 cm^3</td>
                    </tr>
                    <c:forEach items="${size}" var="s">
                        <tr>
                            <td>${s.size}</td>
                            <td>${s.sizeCost}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="tariff_weight-table">
                <table>
                    <tr>
                        <td>Weight</td>
                        <td>Cost for 1 kg</td>
                    </tr>
                    <c:forEach items="${weight}" var="w">
                        <tr>
                            <td>${w.weight}</td>
                            <td>${w.weightCost}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="tariff_distance-table">
                <table>
                    <tr>
                        <td>Distance</td>
                        <td>Cost</td>
                    </tr>
                    <c:forEach items="${distance}" var="d">
                        <tr>
                            <td>${d.distance}</td>
                            <td>${d.distanceCost}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            </div>
        </div>
    </main>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>