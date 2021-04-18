<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.language != null ? sessionScope.language : 'ru'}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="br" />

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
                <input class="input-field" type="text" name="login" placeholder="Login" value="${requestScope.login}" />
                <input class="input-field" type="password" name="password" placeholder="Password" />
                <input class="submit-btn" type="submit" value="<fmt:message key="header.login" bundle="${br}"/>">
            </form>
            <c:if test="${requestScope.errorMessage != null}">
                <div class="error-box">
                    <p>
                        <fmt:message key="${requestScope.errorMessage}" bundle="${br}" />
                    </p>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>