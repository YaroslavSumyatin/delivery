<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<h:head title="Forming Waybill"/>
<body>
<div class="container">
    <jsp:include page="_header.jsp"></jsp:include>
    <main class="container_main">
        <div class="form_waybill-content">
            <form id="waybill_form" action="${pageContext.request.contextPath}/manage/applications/formwaybill"
                  method="post">
                <input type="hidden" name="application" value="${app.id}">
                <input type="hidden" name="manager" value="${manager.id}">
                <fmt:message key="application.from"/>: ${addressFrom} <br>
                <fmt:message key="application.to"/>: ${addressTo} <br>
                <fmt:message key="application.baggage_type"/>: <fmt:message
                    key="application.baggage_type.${app.baggageType}"/> <br>
                <fmt:message key="size"/>: ${app.size} <fmt:message key="cm.3"/> <br>
                <fmt:message key="application.weight"/>: ${app.weight} <fmt:message key="kg"/> <br>
                <fmt:message key="cost"/>: <input type="number" name="cost" value="${tariff.deliveryCost}" min="1"
                                                  step="any"> <fmt:message key="uah"/>
                <input type="button" value="<fmt:message key="form_waybill.confirm_waybill"/>" onclick="return check()"
                       required/>
            </form>
        </div>
    </main>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
<script type="text/javascript">
    function check() {
        const form = document.getElementById("waybill_form");
        if (confirm('<fmt:message key="are_you_sure"/>')) {
            form.submit()
        }
    }
</script>
</body>
</html>