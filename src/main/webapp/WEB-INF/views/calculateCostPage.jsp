<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<h:head title="Calculate Cost"/>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <main class="container_main">
        <div class="page-content">
            <div class="form">
                <form action="${pageContext.request.contextPath}/calculation" method="post">
                    <div class="application_content-select">
                        <fmt:message key="application.from"/>
                        <select id="dep1" name="departmentFrom" class="application-department-from" required>
                            <option value="">--<fmt:message key="application.choose_department"/>--</option>
                            <c:forEach items="${departments}" var="d">
                                <option value="${d.id}" ${idFrom eq d.id ? "selected" : ""}>
                                        ${d.index.concat(", ").concat(d.address)}
                                </option>
                            </c:forEach>
                        </select>
                        <fmt:message key="application.to"/>
                        <select id="dep2" name="departmentTo" class="application-department-to" required>
                            <option value="">--<fmt:message key="application.choose_department"/>--</option>
                            <c:forEach items="${departments}" var="d">
                                <option value="${d.id}" ${idTo eq d.id ? "selected" : ""} >
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
                    </div>
                    <input type="submit" value="<fmt:message key="calculate_cost.calculate"/>">
                </form>
                <c:if test="${not empty cost}">
                    <fmt:message key="calculate_cost.calculated_cost"/>:&nbsp;${cost}&nbsp;<fmt:message key="uah"/>
                </c:if>
            </div>
        </div>
    </main>
    <jsp:include page="_footer.jsp"/>
</div>
</body>
</html>