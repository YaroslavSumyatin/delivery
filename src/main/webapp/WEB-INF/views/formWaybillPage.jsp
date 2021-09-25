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
        <div class="form_waybill-content">
            <form id="waybill_form" action="${pageContext.request.contextPath}/manage/applications/formwaybill" method="post">
                <input type="hidden" name="application" value="${app.id}">
                <input type="hidden" name="manager" value="${manager.id}">
                From: ${addressFrom} <br>
                To: ${addressTo} <br>
                BaggageType: ${app.baggageType} <br>
                Size: ${app.size} <br>
                Weight: ${app.weight} <br>
                Cost: <input type="number" name="cost" value="${tariff.deliveryCost}"> UAH
                <input type="button" value="Confirm" onclick="return check()" required/>
            </form>
        </div>
    </main>
    <jsp:include page="_footer.jsp"></jsp:include>
</div>
<script type="text/javascript">
    function check(){
        const form = document.getElementById("waybill_form");
        if (confirm('Are you sure?')){
            form.submit()
        }
    }
</script>
</body>
</html>