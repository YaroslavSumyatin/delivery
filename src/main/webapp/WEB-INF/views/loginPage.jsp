<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="login.login_page"/></title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <main class="container_main">
        <div class="page-content">
            <div class="form">
                <p style="color: red;"><c:if test="${not empty errorMessage}"><fmt:message
                        key="login.error_message"/></c:if></p>
                <form method="POST" action="${pageContext.request.contextPath}/login">
                    <div>
                        <label for="login"><fmt:message key="login"/></label>
                        <input id="login" type="text" name="login" value="${user.login}" pattern="[A-Za-z]{3,20}"/>
                    </div>
                    <div>
                        <label for="pass"><fmt:message key="password"/></label>
                        <input id="pass" type="password" name="password" />
                    </div>
                    <div>
                        <input type="submit" value="<fmt:message key="login.sign_in"/>"/>
                        <a href="${pageContext.request.contextPath}/registration"><fmt:message
                                key="login.create_acc"/></a>
                    </div>
                </form>
            </div>
        </div>
    </main>
    <jsp:include page="_footer.jsp"/>
</div>
</body>
</html>