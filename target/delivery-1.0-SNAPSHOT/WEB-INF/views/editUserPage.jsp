<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="edit_user.edit_user_page"/></title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"></jsp:include>
    <main class="container_main">
        <div class="edit-user_content">
            <form name="form" id="update_user" action="${pageContext.request.contextPath}/profile/edit" method="post">
                <input type="hidden" name="id" value="${user.id}">
                <input type="hidden" name="role" value="${user.role}">
                <fmt:message key="login"/>: <input type="text" name="login" value="${user.login}" required/><br>
                <fmt:message key="email"/>: <input type="text" name="email" value="${user.email}" required/><br>
                <fmt:message key="name"/>: <input type="text" name="name" value="${user.name}" required/><br>
                <fmt:message key="surname"/>: <input type="text" name="surname" value="${user.surname}" required/><br>
                <fmt:message key="edit_user.write_password"/>: <input type="password" name="newPassword1"><br>
                <fmt:message key="edit_user.repeat_password"/>: <input type="password" name="newPassword2"><br>
                <input type="submit" value="<fmt:message key="edit_user.confirm_changes"/>" onclick="return check()">
            </form>
            <a href="${pageContext.request.contextPath}/profile"><fmt:message key="edit_user.cancel"/></a>
        </div>
    </main>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
<script type="text/javascript">
    function check(){
        const form = document.getElementById("update_user");
        if (confirm('<fmt:message key="are_you_sure"/>')){
            form.submit()
        }
    }
</script>
</body>
</html>
