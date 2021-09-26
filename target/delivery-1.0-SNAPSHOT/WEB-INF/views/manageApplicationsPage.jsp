<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         import="com.delivery.database.entities.Application" pageEncoding="UTF-8"%>
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
        <div class="manage_applications-content">
            <c:forEach items="${applications}" var="a">
                <c:if test="${a.state eq Application.STATE_IN_PROCESSING}">
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
                    <form action="${pageContext.request.contextPath}/manage/applications/formwaybill">
                        <input type="hidden" name="application" value="${a.id}">
                        <input type="submit" value="Create waybill"/>
                    </form>
                </div>
                </c:if>
            </c:forEach>
        </div>
    </main>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>