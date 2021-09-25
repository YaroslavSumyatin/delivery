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
        <div class="management">
            <form action="${pageContext.request.contextPath}/manage/waybills">
                <input type="submit" value="Unpaid Waybills">
            </form>
            <form action="${pageContext.request.contextPath}/manage/applications">
                <input type="submit" value="Unprocessed Applications">
            </form>
            <form>
                <input type="submit" value="Get a Report">
            </form>
        </div>
    </main>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>