<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.language != null ? sessionScope.language : 'ru'}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>

<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="static/style.css">
</head>
<body>

<jsp:include page="fragments/header.jsp"/>

<div id="main">
    <c:if test="${requestScope.successMessage != null}">
        <div class="success-box">
            <p>
                <fmt:message key="${requestScope.successMessage}" bundle="${rb}"/>
            </p>
        </div>
    </c:if>
    <custom:error bundle="${rb}" errorName="${requestScope.errorMessage}"/>

</div>
</body>
</html>