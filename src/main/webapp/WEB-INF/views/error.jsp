<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="error.error_page"/></title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"></jsp:include>
    <main class="container_main">
        <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
        <c:choose>
            <c:when test="${code == 403}">
                <fmt:message key="error.403"/>
            </c:when>
            <c:when test="${code == 404}">
                <fmt:message key="error.404"/>
            </c:when>
            <c:otherwise>
                <fmt:message key="error.500"/>
            </c:otherwise>
        </c:choose>
    </main>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>