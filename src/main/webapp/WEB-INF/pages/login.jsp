<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.language != null ? sessionScope.language : 'ru'}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb" />

<html>
<head>
    <link rel="stylesheet" href="static/style.css">
</head>
<body>
    <jsp:include page="fragments/header.jsp" />
    <div id="main">
        <div class="login-form">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="login">
                <input class="input-field" type="text" name="login" placeholder="<fmt:message bundle="${rb}" key="login.placeholder.login"/>" value="${requestScope.login}" />
                <input class="input-field" type="password" name="password" placeholder="<fmt:message bundle="${rb}" key="login.placeholder.password"/>" />
                <input class="submit-btn" type="submit" value="<fmt:message key="header.login" bundle="${rb}"/>">
            </form>
            <custom:error bundle="${rb}" errorName="${requestScope.errorMessage}"/>
        </div>
    </div>
</body>
</html>