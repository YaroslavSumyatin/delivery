<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<h:head title="Payment Completed"/>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <main class="container_main">
        <div class="label-page">
            <fmt:message key="payment.payment_completed"/>
        </div>
    </main>
    <jsp:include page="_footer.jsp"/>
</div>
</body>
</html>