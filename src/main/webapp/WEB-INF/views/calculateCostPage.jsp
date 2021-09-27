<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.delivery.database.entities.Application"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="calculate_cost.calculate_cost_page"/></title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <main class="container_main">
        <div class="calculate_content">
            <form action="${pageContext.request.contextPath}/calculation" method="post">
                <div class="application_content-select">
                    <fmt:message key="application.from"/>:
                    <select name="departmentFrom" class="application-department-from" required>
                        <option value="">--<fmt:message key="application.choose_department"/>--</option>
                        <c:forEach items="${departments}" var="d">
                            <option value="${d.id}" ${idFrom eq d.id ? "selected" : ""}>
                                    ${d.index.concat(", ").concat(d.address)}
                            </option>
                        </c:forEach>
                    </select>
                    <fmt:message key="application.to"/>
                    <select name="departmentTo" class="application-department-to" required>
                        <option value="">--<fmt:message key="application.choose_department"/>--</option>
                        <c:forEach items="${departments}" var="d">
                            <option value="${d.id}" ${idTo eq d.id ? "selected" : ""} >
                                    ${d.index.concat(", ").concat(d.address)}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="application_content-inputs">
                    <fmt:message key="application.length"/>: <input type="number" name="length" value="${length}" required/><fmt:message key="cm"/>
                    <fmt:message key="application.width"/>: <input type="number" name="width" value="${width}" required/><fmt:message key="cm"/>
                    <fmt:message key="application.height"/>: <input type="number" name="height" value="${height}" required/><fmt:message key="cm"/>
                    <fmt:message key="application.weight"/>: <input type="number" name="weight" value="${weight}" required><fmt:message key="kg"/>
                </select>
                </div>
                <input type="submit" value="<fmt:message key="calculate_cost.calculate"/>">
            </form>
            <c:if test="${not empty cost}">
                <fmt:message key="calculate_cost.calculated_cost"/>:&nbsp;${cost}&nbsp;<fmt:message key="uah"/>
            </c:if>
        </div>
    </main>
    <jsp:include page="_footer.jsp"/>
</div>
</body>
</html>