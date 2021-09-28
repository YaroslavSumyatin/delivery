<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="com.delivery.database.entities.Waybill" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="waybills.waybills_page"/></title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <main class="container_main">
        <div class="manage_waybills-content">
            <c:forEach items="${waybills}" var="w">
                <c:if test="${w.state eq Waybill.STATE_WAITING_FOR_PAYMENT}">
                    <div class="manage_waybills-item">
                        <c:forEach items="${users}" var="u">
                            <c:if test="${w.userId eq u.id}">
                                <fmt:message key="waybills.manager"/>: ${u.name.concat(" ").concat(u.surname)}<br>
                            </c:if>
                        </c:forEach>
                        <c:forEach items="${apps}" var="a">
                            <c:if test="${w.applicationId eq a.id}">
                                <fmt:message key="application.baggage_type"/>: <fmt:message
                                    key="application.baggage_type.${a.baggageType}"/><br>
                                <fmt:message key="waybills.state"/>: <fmt:message key="waybills.state.${a.state}"/><br>
                                <fmt:message key="size"/>: ${a.size} <fmt:message key="cm.3"/> <br>
                                <fmt:message key="application.weight"/>: ${a.weight} <fmt:message key="kg"/> <br>
                            </c:if>
                        </c:forEach>
                        <fmt:message key="cost"/>: ${w.cost} <fmt:message key="uah"/>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </main>
    <jsp:include page="_footer.jsp"/>
</div>
</body>
</html>