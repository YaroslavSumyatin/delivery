<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.delivery.database.entities.Application" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="profile.profile_page"/></title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
<jsp:include page="_header.jsp"/>
<main class="container_main">
    <div class="userinfo_content">
        <fmt:message key="name"/>: ${user.name} <br>
        <fmt:message key="surname"/>: ${user.surname} <br>
        <fmt:message key="email"/>: ${user.email} <br>
        <a href="${pageContext.request.contextPath}/profile/edit"><fmt:message key="profile.edit_profile"/></a>&nbsp;
        <a id="delete_link" href="${pageContext.request.contextPath}/profile/delete" onclick="return check()"><fmt:message key="profile.delete_profile"/></a><br>
        <div class="userprofile_applications">
            <c:forEach items="${applications}" var="a">
                <c:if test="${not (a.state eq Application.STATE_SENT)}">
                <div class="userprofile_applications-item">
                    <c:forEach items="${departments}" var="d">
                        <c:if test="${a.department1Id eq d.id}">
                            <fmt:message key="application.from"/>: ${d.index.concat(", ").concat(d.address)}<br>
                        </c:if>
                        <c:if test="${a.department2Id eq d.id}">
                            <fmt:message key="application.to"/>: ${d.index.concat(", ").concat(d.address)}<br>
                        </c:if>
                    </c:forEach>
                    <fmt:message key="application.baggage_type"/>: <fmt:message key="application.baggage_type.${a.baggageType}"/><br>
                    <fmt:message key="application.state"/>: <fmt:message key="application.state.${a.state}"/> <br>
                    <c:if test="${a.state eq Application.STATE_WAITING_FOR_PAYMENT}">
                        <form action="${pageContext.request.contextPath}/payment">
                        <c:forEach items="${waybills}" var="w">
                            <c:if test="${w.applicationId eq a.id}">
                                <fmt:message key="cost"/>: ${w.cost} <fmt:message key="uah"/>
                            </c:if>
                        </c:forEach>
                            <input type="hidden" name="application" value="${a.id}">
                            <input type="submit" value="<fmt:message key="profile.pay"/>"/>
                        </form>
                    </c:if>
                    <c:if test="${not (a.state eq Application.STATE_SENT)}">
                        <form id="delete_app${a.id}" action="${pageContext.request.contextPath}/profile" method="post">
                            <input type="hidden" name="application" value="${a.id}">
                            <input type="button" value="<fmt:message key="profile.cancel_applications"/>" onclick="return check(${a.id})"/>
                        </form>
                    </c:if>
                </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</main>
<jsp:include page="_footer.jsp"/>
</div>
<script type="text/javascript">
    function check(){
        confirm('<fmt:message key="are_you_sure"/>')
    }

    function check(a){
        const form = document.getElementById("delete_app".concat(a));
        if (confirm('<fmt:message key="are_you_sure"/>')){
            form.submit()
        }
    }
</script>
</body>
</html>