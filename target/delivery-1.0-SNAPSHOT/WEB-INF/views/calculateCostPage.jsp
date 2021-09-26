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
    <title>Calculate Cost Page</title>
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
                    From:
                    <select name="departmentFrom" class="application-department-from" required>
                        <option value="">--Choose--</option>
                        <c:forEach items="${departments}" var="d">
                            <option value="${d.id}" ${idFrom eq d.id ? "selected" : ""}>
                                    ${d.index.concat(", ").concat(d.address)}
                            </option>
                        </c:forEach>
                    </select>
                    To:
                    <select name="departmentTo" class="application-department-to" required>
                        <option value="">--Choose--</option>
                        <c:forEach items="${departments}" var="d">
                            <option value="${d.id}" ${idTo eq d.id ? "selected" : ""} >
                                    ${d.index.concat(", ").concat(d.address)}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="application_content-inputs">
                    Length: <input type="number" name="length" value="${length}" required/>
                    Width: <input type="number" name="width" value="${width}" required/>
                    Height: <input type="number" name="height" value="${height}" required/>
                    Weight: <input type="number" name="weight" value="${weight}" required>
                </select>
                </div>
                <input type="submit" value="Calculate">
            </form>
            ${not empty cost ? "Calculated cost ".concat(cost).concat(" UAH") : ""}
        </div>
    </main>
    <jsp:include page="_footer.jsp"/>
</div>
</body>
</html>