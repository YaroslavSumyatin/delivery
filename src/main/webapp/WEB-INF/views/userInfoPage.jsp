<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.delivery.database.entities.Application" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="container">
<jsp:include page="_header.jsp"></jsp:include>
<main class="container_main">
    <div class="userinfo_content">
        Name: ${user.name} <br>
        Surname: ${user.surname} <br>
        Email: ${user.email} <br>
        <a href="${pageContext.request.contextPath}/profile/edit">Edit userinfo</a>&nbsp;
        <a id="delete_link" href="${pageContext.request.contextPath}/profile/delete" onclick="return check()">Delete an account</a><br>
        <div class="userprofile_applications">
            <c:forEach items="${applications}" var="a">
                <div class="userprofile_applications-item">
                    <c:forEach items="${departments}" var="d">
                        <c:if test="${a.department1Id eq d.id}">
                            From: ${d.index.concat(", ").concat(d.address)}<br>
                        </c:if>
                        <c:if test="${a.department2Id eq d.id}">
                            To: ${d.index.concat(", ").concat(d.address)}<br>
                        </c:if>
                    </c:forEach>
                    BaggageType: ${a.baggageType}<br>
                    State: ${a.state}<br>
                    <c:if test="${a.state eq Application.STATE_WAITING_FOR_PAYMENT}">
                        <form action="${pageContext.request.contextPath}/payment?application=${a.id}">
                            <input type="submit" value="Pay"/>
                        </form>
                    </c:if>
                    <c:if test="${not (a.state eq Application.STATE_SENT)}">
                        <form id="delete_app${a.id}" action="${pageContext.request.contextPath}/profile" method="post">
                            <input type="hidden" name="application" value="${a.id}">
                            <input type="button" value="Cancel Application" onclick="return check(${a.id})"/>
                        </form>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
<jsp:include page="_footer.jsp"></jsp:include>
</div>
<script type="text/javascript">
    function check(){
        confirm('Are u sure?')
    }

    function check(a){
        const form = document.getElementById("delete_app".concat(a));
        if (confirm('Are you sure?')){
            form.submit()
        }
    }
</script>
</body>
</html>