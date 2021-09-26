<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <jsp:include page="_header.jsp"></jsp:include>
    <main class="container_main">
        <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
        <c:choose>
            <c:when test="${code == 404}">
                Oops, page not found!
            </c:when>
            <c:when test="${code == 500}">
                There was a mistake by our side. Don't worry we will fix it sooner.
            </c:when>
            <c:otherwise>
                <c:out value="${requestScope['javax.servlet.error.message']}"/>
            </c:otherwise>
        </c:choose>
    </main>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>