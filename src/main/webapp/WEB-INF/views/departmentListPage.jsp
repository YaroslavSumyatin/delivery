<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${not empty sessionScope.lang ? sessionScope.lang : 'ua'}"/>
<fmt:setBundle basename="resources"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<h:head title="Departments"/>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <main class="container_main">
        <div class="container_departments">
            <div class="departments_sidebar">
                <a href="${pageContext.request.contextPath}/departments"><fmt:message key="departments.all_cities"/></a><br/>
                <c:forEach items="${cities}" var="city">
                    <a href="${pageContext.request.contextPath}/departments?city=${city.name}">${city.name}</a><br/>
                </c:forEach>
            </div>
            <div class="departments_content">
                <c:forEach items="${departments}" var="d">
                    <div class="departments_item">
                        <div class="departments_item-index">
                                ${d.index}
                        </div>
                        <div class="departments_item-address_wrapper">
                            <div class=departments_item-address">
                                <div>
                                        ${d.address},&nbsp;
                                </div>
                                <c:forEach items="${cities}" var="city">
                                    <c:if test="${city.id.equals(d.cityId)}">
                                        <div>${city.name.concat(", обл. ").concat(city.region)}</div>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="departments_item-number">
                                <fmt:message key="departments.department"/>&nbsp;№&nbsp;${d.number}
                            </div>
                        </div>
                        <div class="departments_item-buttons_wrapper">
                            <form action="${pageContext.request.contextPath}/application">
                                <input type="hidden" name="departmentFrom" value="${d.index}">
                                <input type="submit"
                                       value="<fmt:message key="departments.order_delivery_from_this_department"/>">
                            </form>
                            <form action="${pageContext.request.contextPath}/application">
                                <input type="hidden" name="departmentTo" value="${d.index}">
                                <input type="submit"
                                       value="<fmt:message key="departments.order_delivery_to_this_department"/>">
                            </form>
                        </div>
                    </div>
                </c:forEach>
                <div class="departments_content-pages">
                    <table align="center" border="1" cellpadding="5" cellspacing="5">
                        <tr>
                            <td><a
                                    <c:if test="${curPage != 1}">
                                        href="${pageContext.request.contextPath}/departments?${not empty city ? "city=".concat(city).concat("&") : ''}page=${curPage - 1}"
                                    </c:if>
                            >${"<"}</a></td>
                            <c:forEach begin="1" end="${numberOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${curPage eq i}">
                                        <td>${i}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/departments?${not empty city ? "city=".concat(city).concat("&") : ''}page=${i}">${i}</a>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <td><a
                                    <c:if test="${curPage lt numberOfPages}">
                                        href="${pageContext.request.contextPath}/departments?${not empty city ? "city=".concat(city).concat("&") : ''}page=${curPage + 1}"
                                    </c:if>
                            >${">"}</a></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>
