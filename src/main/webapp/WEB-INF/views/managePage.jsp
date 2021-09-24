<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         import="com.delivery.database.entities.Application" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <div class="manage_content">
            <c:forEach items="${applications}" var="a">
                <div class="manage_applications-item">
                    <c:forEach items="${departments}" var="d">
                        <c:if test="${a.department1Id eq d.id}">
                            From: ${d.index.concat(", ").concat(d.address)}<br>
                        </c:if>
                        <c:if test="${a.department2Id eq d.id}">
                            To: ${d.index.concat(", ").concat(d.address)}<br>
                        </c:if>
                    </c:forEach>
                    BaggageType: ${a.baggageType}<br>
                    State: ${a.state}<br>
                    Size: ${a.size}<br>
                    Weight: ${a.weight}<br>
                    <form action="${pageContext.request.contextPath}/manage/waybill?application=${a.id}">
                        <input type="submit" value="Create waybill"/>
                    </form>
                </div>
            </c:forEach>
        </div>
    </main>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>