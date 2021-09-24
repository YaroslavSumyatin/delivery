<%@ page import="com.delivery.database.entities.User" %>
<%@ page import="com.delivery.filters.Utils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="container_header">
    <div class="header_menu">
        <c:set var="activeLink" value="${requestScope['javax.servlet.forward.request_uri']}"/>
        <c:set var="path" value="${pageContext.request.contextPath}"/>
            <a class="${activeLink.equals(path.concat("/")) || activeLink.equals(path.concat("/home")) ? 'active' : 'none'}"
               href="${path}/">Home</a>
            <a class="${activeLink.equals(path.concat("/departments")) ? 'active' : 'none'}"
               href="${path}/departments">Departments</a>
            <a class="${activeLink.equals(path.concat("/tariffs")) ? 'active' : 'none'}"
               href="${path}/tariffs">Tariffs</a>
            <a class="${activeLink.equals(path.concat("/calculation")) ? 'active' : 'none'}"
               href="${path}/calculation">Calculate The Cost</a>
            <a class="${activeLink.equals(path.concat("/application")) ? 'active' : 'none'}"
               href="${path}/application">Make an application</a>
            <div class="menu_right">
            <%
                User user = Utils.getUserInSession(session);
                if (user == null) {
            %>

                <a class="${activeLink.equals(path.concat("/login")) ? 'active' : 'none'}"
                   href="${pageContext.request.contextPath}/login">Login</a>
                <a class="${activeLink.equals(path.concat("/registration")) ? 'active' : 'none'}"
                   href="${path}/registration">Create New Account</a>
            <%
                } else {
                    if (user.getRole().equals(User.ROLE_MANAGER)) {
            %>
                <a class="${activeLink.equals(path.concat("/manage")) ? 'active' : 'none'}"
                   href="${pageContext.request.contextPath}/manage">Manage Applications</a>
                <% } %>
                <a class="${activeLink.equals(path.concat("/profile")) ? 'active' : 'none'}"
                        href="${path}/profile">My Account Info</a>
                <a href="${path}/logout">Logout</a>
            <% } %>
            </div>
    </div>
</header>
