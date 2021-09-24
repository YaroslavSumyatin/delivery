<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employees</title>
</head>
<body>
    <c:if test="${not empty result}">
        <h5><c:out value="${result}"></c:out></h5>
    </c:if>

    <form action="employee.do" method="post">
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <td>Employee Name:</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>Salary:</td>
                <td><input type="text" name="salary"></td>
            </tr>
            <tr>
                <td>Department Name:</td>
                <td><input type="text" name="deptName"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" name="submit" value="Insert"></td>
            </tr>
        </table>
    </form>
</body>
</html>
