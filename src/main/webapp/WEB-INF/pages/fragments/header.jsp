<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<head>
    <link rel="stylesheet" href="static/header.css">
</head>

<style>
.navigation li {
    width: ${sessionScope.user != null ? 50 : 100}%;
}
</style>

<fmt:setLocale value="en_US" scope="session"/>
<fmt:bundle basename="pagecontent" prefix="header.">
<div class="header">

    <!---   Logo    --->
    <h2 class="logo">
        <fmt:message key="logo" />
    </h2>

    <!---     Navigation       --->
    <ul class="navigation">
        <li><a href="${pageContext.request.contextPath}/controller?command=books">
            <fmt:message key="books" />
        </a></li>
        <c:if test="${sessionScope.user != null}">
            <li><a href="">${sessionScope.user.login}</a></li>
        </c:if>
    </ul>

    <!---   Sign in button    --->
    <c:if test="${sessionScope.user == null}">
        <fmt:message key="sign_in" var="sign_in" />
        <form method="get" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="login">
            <input type="submit" class="login-btn" value="${sign_in}">
        </form>
    </c:if>

    <!---   Sign out button    --->
    <c:if test="${sessionScope.user != null}">
        <fmt:message key="sign_out" var="sign_out" />
        <form method="get" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="logout">
            <input type="submit" class="login-btn" value="${sign_out}">
        </form>
    </c:if>

</div>
</fmt:bundle>