<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"></jsp:include>
    <main class="container_main">
        <div>
            ${errorMessage}
        </div>
        <div class="edit-user_content">
            <form name="form" id="update_user" action="${pageContext.request.contextPath}/profile/edit" method="post">
                <input type="hidden" name="id" value="${user.id}">
                <input type="hidden" name="role" value="${user.role}">
                Login: <input type="text" name="login" value="${user.login}" required/><br>
                Email: <input type="text" name="email" value="${user.email}" required/><br>
                Name: <input type="text" name="name" value="${user.name}" required/><br>
                Surname: <input type="text" name="surname" value="${user.surname}" required/><br>
                Write new password(only if you want to change it): <input type="password" name="newPassword1"><br>
                Repeat new password: <input type="password" name="newPassword2"><br>
                <input type="submit" value="Submit" onclick="return check()">
            </form>
            <a href="${pageContext.request.contextPath}/profile">Cancel</a>
        </div>
    </main>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
<script type="text/javascript">
    function check(){
        const form = document.getElementById("update_user");
        if (confirm('Are you sure?')){
            form.submit()
        }
    }
</script>
</body>
</html>
