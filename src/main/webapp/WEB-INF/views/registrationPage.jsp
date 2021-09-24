<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
<jsp:include page="_header.jsp"></jsp:include>
<main class="container_main">
<h3>Login Page</h3>
<p style="color: red;">${errorMessage}</p>
<form method="POST" action="${pageContext.request.contextPath}/registration">
    <table border="0">
        <tr>
            <td>Login</td>
            <td><input type="text" name="login" value= "${user.login}" /> </td>
        </tr>
        <tr>
            <td>E-mail</td>
            <td><input type="text" name="email" value= "${user.email}" /> </td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" value= "${user.name}" /> </td>
        </tr>
        <tr>
            <td>Surname</td>
            <td><input type="text" name="surname" value= "${user.surname}" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /> </td>
        </tr>
        <tr>
            <td>Repeat Password</td>
            <td><input type="password" name="password2" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</main>
<jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>