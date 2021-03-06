<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<head>
    <link rel="stylesheet" href="static/header.css">
</head>

<style>
.navigation li {
    width:
    <c:choose>
        <c:when test="${sessionScope.user == null}">100%</c:when>
        <c:when test="${sessionScope.user.role == 'READER'}">50%</c:when>
        <c:when test="${sessionScope.user.role == 'LIBRARIAN'}">50%</c:when>
        <c:when test="${sessionScope.user.role == 'ADMIN'}">50%</c:when>
    </c:choose>;
}
</style>

<fmt:setLocale value="${sessionScope.language != null ? sessionScope.language : 'ru'}" scope="session"/>
<fmt:bundle basename="pagecontent" prefix="header.">

<div class="header">

    <ul class="localization">
        <li><a href="${pageContext.request.contextPath}/controller?${pageContext.request.queryString}&lang=ru">RU</a></li>
        <li><a href="${pageContext.request.contextPath}/controller?${pageContext.request.queryString}&lang=en">EN</a></li>
        <li><a href="${pageContext.request.contextPath}/controller?${pageContext.request.queryString}&lang=be">BE</a></li>
    </ul>

    <!---     Navigation       --->
    <ul class="navigation">
        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <li><a href="${pageContext.request.contextPath}/controller?command=library"><fmt:message key="library" /></a></li>
            </c:when>
            <c:when test="${sessionScope.user.role == 'READER'}">
                <li><a href="${pageContext.request.contextPath}/controller?command=library"><fmt:message key="library" /></a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=profile">${sessionScope.user.login}</a></li>
            </c:when>
            <c:when test="${sessionScope.user.role == 'LIBRARIAN'}">
                <li><a href="${pageContext.request.contextPath}/controller?command=library"><fmt:message key="library" /></a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=librarian"><fmt:message key="librarian" /></a></li>
            </c:when>
            <c:when test="${sessionScope.user.role == 'ADMIN'}">
                <li><a href="${pageContext.request.contextPath}/controller?command=library"><fmt:message key="library" /></a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=admin"><fmt:message key="admin" /></a></li>
            </c:when>
        </c:choose>
        <c:if test="${sessionScope.user != null}">

        </c:if>

    </ul>

    <!---   Sign in button    --->
    <c:if test="${sessionScope.user == null}">
        <fmt:message key="login" var="login"/>
        <form method="get" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="login">
            <input type="submit" class="login-btn" value="${login}">
        </form>
    </c:if>

    <!---   Sign out button    --->
    <c:if test="${sessionScope.user != null}">
        <fmt:message key="signOut" var="signOut" />
        <form method="get" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="logout">
            <input type="submit" class="login-btn" value="${signOut}">
        </form>
    </c:if>

</div>
</fmt:bundle>