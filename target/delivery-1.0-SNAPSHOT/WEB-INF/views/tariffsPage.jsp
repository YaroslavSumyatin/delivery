<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="tariffs.tariffs_page"/></title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <main class="container_main">
        <div class="tariff_table-container">
            <div class="tariff_table-container_inner">
            <div class="tariff_size-table">
                <table>
                    <tr>
                        <td><fmt:message key="size"/>(<fmt:message key="cm.3"/>)</td>
                        <td><fmt:message key="cost"/>(<fmt:message key="uah"/>)</td>
                    </tr>
                    <c:forEach items="${size}" var="s">
                        <tr>
                            <td><fmt:message key="tariffs.until"/> ${s.size}</td>
                            <td>${s.sizeCost}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="tariff_weight-table">
                <table>
                    <tr>
                        <td><fmt:message key="application.weight"/>(<fmt:message key="kg"/>)</td>
                        <td><fmt:message key="cost"/>(<fmt:message key="uah"/>)</td>
                    </tr>
                    <c:forEach items="${weight}" var="w">
                        <tr>
                            <td><fmt:message key="tariffs.until"/> ${w.weight}</td>
                            <td>${w.weightCost}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="tariff_distance-table">
                <table>
                    <tr>
                        <td><fmt:message key="tariffs.distance"/></td>
                        <td><fmt:message key="cost"/>(<fmt:message key="uah"/>)</td>
                    </tr>
                    <c:forEach items="${distance}" var="d">
                        <tr>
                            <td><fmt:message key="tariffs.distance.${d.distance}"/></td>
                            <td>${d.distanceCost}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            </div>
        </div>
    </main>
    <jsp:include page="_footer.jsp"/>
</div>
</body>
</html>