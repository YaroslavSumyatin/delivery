<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.delivery.database.entities.Application"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Application Page</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"></jsp:include>
    <main class="container_main">
        <div class="application_content">
            <form action="${pageContext.request.contextPath}/application" method="post">
                <input type="hidden" name="user" value="${user.id}"/>
                <div class="application_content-select">
                    From:
                    <select name="departmentFrom" class="application-department-from">
                        <option>--Choose--</option>
                        <c:forEach items="${departments}" var="d">
                            <option value="${d.id}" ${indexFrom eq d.index ? "selected" : ""}>
                                    ${d.index.concat(", ").concat(d.address)}
                            </option>
                        </c:forEach>
                    </select>
                    To:
                    <select name="departmentTo" class="application-department-to">
                        <option>--Choose--</option>
                        <c:forEach items="${departments}" var="d">
                            <option value="${d.id}" ${indexTo eq d.index ? "selected" : ""} >
                                    ${d.index.concat(", ").concat(d.address)}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="application_content-inputs">
                    Length: <input type="number" name="length"/>
                    Width: <input type="number" name="width">
                    Height: <input type="number" name="height"/>
                    Weight: <input type="number" name="weight">
                    Receive Date: <input type="date" name="receiveDate"/>
                    Baggage Type: <select name="baggageType">
                        <option value="${Application.BAGGAGE_DOCUMENTS}">${Application.BAGGAGE_DOCUMENTS}</option>
                        <option value="${Application.BAGGAGE_FRAGILE}">${Application.BAGGAGE_FRAGILE}</option>
                        <option value="${Application.BAGGAGE_CLOTHES}">${Application.BAGGAGE_CLOTHES}</option>
                        <option value="${Application.BAGGAGE_PACKAGE}">${Application.BAGGAGE_PACKAGE}</option>
                    </select>
                </div>
                <input type="submit" value="Submit">
            </form>
        </div>
    </main>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>