<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         import="com.delivery.database.entities.Application" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<h:head title="Unprocessed Applications"/>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <main class="container_main">
        <div class="manage_applications-content">
            <c:forEach items="${applications}" var="a">
            <c:if test="${a.state eq Application.STATE_IN_PROCESSING}">
            <div class="manage_applications-item">
                <c:forEach items="${departments}" var="d">
                <c:if test="${a.department1Id eq d.id}">
                    <fmt:message key="application.from"/>: ${d.index.concat(", ").concat(d.address)}<br>
                </c:if>
                <c:if test="${a.department2Id eq d.id}">
                    <fmt:message key="application.to"/>: ${d.index.concat(", ").concat(d.address)}<br>
                </c:if>
                </c:forEach>
                    <fmt:message key="application.baggage_type"/>: <fmt:message
                    key="application.baggage_type.${a.baggageType}"/><br>
                    <fmt:message key="application.state"/>: <fmt:message key="application.state.${a.state}"/><br>
                    <fmt:message key="size"/>: ${a.size} <fmt:message key="cm.3"/><br>
                    <fmt:message key="application.weight"/>: ${a.weight} <fmt:message key="kg"/><br>
                <form action="${pageContext.request.contextPath}/manage/applications/formwaybill">
                    <input type="hidden" name="application" value="${a.id}">
                    <input type="submit" value=<fmt:message key="manage_apps.form_waybill"/>"/>
                    </form>
                </div>
                </c:if>
            </c:forEach>
        </div>
    </main>
    <jsp:include page="_footer.jsp"/>
</div>
</body>
</html>