<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="manage.manage_page"/></title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <main class="container_main">
        <div class="management">
            <form action="${pageContext.request.contextPath}/manage/waybills">
                <input type="submit" value="<fmt:message key="manage.unpaid_waybills"/>">
            </form>
            <form action="${pageContext.request.contextPath}/manage/applications">
                <input type="submit" value="<fmt:message key="manage.unprocessed_applications"/>">
            </form>
        </div>
    </main>
    <jsp:include page="_footer.jsp"/>
</div>
</body>
</html>