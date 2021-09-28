<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.delivery.database.entities.Application" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="application.application_page"/></title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"></jsp:include>
    <main class="container_main">
        <div class="page-content">
            <div class="form">
                <form action="${pageContext.request.contextPath}/application" method="post">
                    <input type="hidden" name="user" value="${user.id}"/>
                    <div class="application_content-select">
                        <fmt:message key="application.from"/>:
                        <select name="departmentFrom" class="application-department-from">
                            <option>--<fmt:message key="application.choose_department"/>--</option>
                            <c:forEach items="${departments}" var="d">
                                <option value="${d.id}" ${indexFrom eq d.index ? "selected" : ""}>
                                        ${d.index.concat(", ").concat(d.address)}
                                </option>
                            </c:forEach>
                        </select>
                        <fmt:message key="application.to"/>:
                        <select name="departmentTo" class="application-department-to">
                            <option>--<fmt:message key="application.choose_department"/>--</option>
                            <c:forEach items="${departments}" var="d">
                                <option value="${d.id}" ${indexTo eq d.index ? "selected" : ""} >
                                        ${d.index.concat(", ").concat(d.address)}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="application_content-inputs">
                        <label for="length"><fmt:message key="application.length"/> (<fmt:message key="cm"/>)</label>
                        <input id="length" type="number" name="length" value="${length}" min="1" step="any" required/>
                        <label for="width"> <fmt:message key="application.width"/> (<fmt:message key="cm"/>)</label>
                        <input id="width" type="number" name="width" value="${width}" min="1" step="any" required/>
                        <label for="height"><fmt:message key="application.height"/> (<fmt:message key="cm"/>)</label>
                        <input id="height" type="number" name="height" value="${height}" min="1" step="any" required/>
                        <label for="weight"><fmt:message key="application.weight"/> (<fmt:message key="kg"/>)</label>
                        <input id="weight" type="number" name="weight" value="${weight}" min="1" step="any" required>
                        <label for="date"><fmt:message key="application.receive_date"/></label>
                        <input id="date" type="date" name="receiveDate" required/>
                        <label for="bt"><fmt:message key="application.baggage_type"/> </label>
                        <select id="bt" name="baggageType" required>
                            <option value="${Application.BAGGAGE_DOCUMENTS}"><fmt:message
                                    key="application.baggage_type.documents"/></option>
                            <option value="${Application.BAGGAGE_FRAGILE}"><fmt:message
                                    key="application.baggage_type.fragile"/></option>
                            <option value="${Application.BAGGAGE_CLOTHES}"><fmt:message
                                    key="application.baggage_type.clothes"/></option>
                            <option value="${Application.BAGGAGE_PACKAGE}"><fmt:message
                                    key="application.baggage_type.package"/></option>
                        </select>
                    </div>
                    <input type="submit" value="<fmt:message key="application.create_application"/>">
                </form>
            </div>
        </div>
    </main>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
<script type="text/javascript">
    date.min = new Date().toISOString().split("T")[0]
</script>
</body>
</html>