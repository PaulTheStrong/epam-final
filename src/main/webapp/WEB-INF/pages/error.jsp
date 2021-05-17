<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language != null ? sessionScope.language : 'ru'}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb" />
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="static/style.css" />
</head>
<body>
    <jsp:include page="fragments/header.jsp" />
    <c:if test="${requestScope.errorMessage != null}">
        <div class="error-box"><fmt:message key="${requestScope.errorMessage}" bundle="${rb}"/></div>
    </c:if>
    <div class="error-box">
        Ошибка <c:out value="${requestScope.statusCode}" />. Пожалйста, вернитесь на <a href="${pageContext.request.contextPath}/">главную</a>.
    </div>
</body>
</html>
