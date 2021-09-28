<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="manage.report"/></title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <main class="container_main">
        <div class="report-content">
            <table>
                <tr>
                    <td><fmt:message key="application.from"/></td>
                    <td><fmt:message key="application.to"/></td>
                    <td><fmt:message key="application.baggage_type"/></td>
                    <td><fmt:message key="application.receive_date"/></td>
                    <td><fmt:message key="cost"/>(<fmt:message key="uah"/>)</td>
                </tr>
                <c:forEach items="${report}" var="r">
                    <tr>
                        <td>${r.address1}</td>
                        <td>${r.address2}</td>
                        <td>
                            <c:if test="${not empty report}">
                                <fmt:message key="application.baggage_type.${r.baggageType}"/>
                            </c:if>
                        </td>
                        <td>${r.date}</td>
                        <td>${r.cost}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </main>
    <jsp:include page="_footer.jsp"/>
</div>
</body>
</html>