<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<main class="container_main">
<h3>Login Page</h3>
<p style="color: red;">${errorMessage}</p>
<form method="POST" action="${pageContext.request.contextPath}/login">
    <table border="0">
        <tr>
            <td>Login</td>
            <td><input type="text" name="login" value= "${user.login}" /> </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="password" value= "${user.password}" /> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/registration">I don't have an account</a>
            </td>
        </tr>
    </table>
</form>
<p style="color:blue;">User Name: tom, password: tom001 or jerry/jerry001</p>
</main>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>