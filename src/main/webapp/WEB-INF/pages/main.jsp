<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Main</title>
        <link rel="stylesheet" href="static/style.css">
    </head>
    <body>
        <jsp:include page="fragments/header.jsp" />
        <div id="main">
            <c:if test="${sessionScope.user != null}">
                <h2>
                    Welcome, ${sessionScope.user.name} ${sessionScope.user.surname}
                </h2>
            </c:if>
            <c:if test="${requestScope.errorMessage != null}">
            <div class="error-box">
                <p>${requestScope.errorMessage}</p>
            </div>
            </c:if>
        </div>
     </body>
</html>