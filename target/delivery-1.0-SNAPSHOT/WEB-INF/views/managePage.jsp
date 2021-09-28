<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<h:head title="Manager Panel"/>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <main class="container_main">
        <div class="page-content">
            <div class="form">
            <form action="${pageContext.request.contextPath}/manage/waybills">
                <input type="submit" value="<fmt:message key="manage.unpaid_waybills"/>">
            </form>
            <form action="${pageContext.request.contextPath}/manage/applications">
                <input type="submit" value="<fmt:message key="manage.unprocessed_applications"/>">
            </form>
            <form action="${pageContext.request.contextPath}/manage/report">
                <fmt:message key="manage.dates"/>
                <input id="date1" type="date" name="date1" required>
                <input id="date2" type="date" name="date2" required>
                <input type="submit" value="<fmt:message key="manage.report"/>">
            </form>
            </div>
        </div>
    </main>
    <jsp:include page="_footer.jsp"/>
</div>
</body>
</html>