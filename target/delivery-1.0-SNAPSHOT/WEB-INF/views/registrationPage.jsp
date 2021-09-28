<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="registration.registration_page"/></title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
<jsp:include page="_header.jsp"></jsp:include>
<main class="container_main">
    <div class="registration-content">
    <p style="color: red;"><c:if test="${not empty errorMessage}"><fmt:message key="registration.error"/></c:if></p>
    <p style="color: red;"><c:if test="${not empty errorPass}"><fmt:message key="registration.error_pass"/></c:if></p>
    <form method="POST" action="${pageContext.request.contextPath}/registration">
        <table border="0">
            <tr>
                <td><fmt:message key="login"/></td>
                <td><input type="text" name="login" value= "${user.login}" /> </td>
            </tr>
            <tr>
                <td><fmt:message key="email"/></td>
                <td><input type="text" name="email" value= "${user.email}" /> </td>
            </tr>
            <tr>
                <td><fmt:message key="name"/></td>
                <td><input type="text" name="name" value= "${user.name}" /> </td>
            </tr>
            <tr>
                <td><fmt:message key="surname"/></td>
                <td><input type="text" name="surname" value= "${user.surname}" /> </td>
            </tr>
            <tr>
                <td><fmt:message key="password"/></td>
                <td><input type="password" name="password" /> </td>
            </tr>
            <tr>
                <td><fmt:message key="repeat_password"/></td>
                <td><input type="password" name="password2" /> </td>
            </tr>
            <tr>
                <td colspan ="2">
                    <input type="submit" value= "<fmt:message key="registration.sign_up"/>" />
                </td>
            </tr>
        </table>
    </form>
    </div>
</main>
<jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>