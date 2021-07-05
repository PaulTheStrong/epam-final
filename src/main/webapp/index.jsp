<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cus" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link rel="stylesheet" href="static/style.css">
    </head>
    <body>
        <c:if test="${sessionScope.user == null}">
            <jsp:forward page="WEB-INF/pages/login.jsp" />
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <jstl:redirect url="/controller?command=library" />
        </c:if>
    </body>
</html>