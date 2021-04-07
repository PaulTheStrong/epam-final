<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="static/style.css">
</head>
<body>

<jsp:include page="fragments/header.jsp"/>
<fmt:setLocale value="${sessionScope.language != null ? sessionScope.language : 'ru'}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="br"/>

<div id="main">
    <c:if test="${requestScope.successMessage != null}">
        <div class="success-box">
            <p>
                <fmt:message key="${requestScope.successMessage}" bundle="${br}"/>
            </p>
        </div>
    </c:if>
    <c:if test="${requestScope.errorMessage != null}">
        <div class="error-box">
            <fmt:message key="${requestScope.errorMessage}" bundle="${br}"/>
        </div>
    </c:if>

</div>
</body>
</html>