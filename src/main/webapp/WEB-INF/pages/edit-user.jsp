<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language != null ? sessionScope.language : 'ru'}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb" />

<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>


<html>
<head>
    <title>Edit users</title>
    <link href="static/style.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="fragments/header.jsp" />
    <div id="main" class="flex-column">
        <c:if test="${requestScope.errorMessage != null}" >
            <custom:error errorName="${requestScope.errorMessage}" bundle="${rb}" />
        </c:if>
        <div class="bookmarks">
            <h2>
                <a href="${pageContext.request.contextPath}/controller?command=admin&edit=books">Books</a>
            </h2>
            <h2>
                <a href="${pageContext.request.contextPath}/controller?command=editUser">Users</a>
            </h2>
        </div>
        <table class="full-width table">
            <thead>
                <tr>
                    <td>Login</td>
                    <td>Name</td>
                    <td>Surname</td>
                    <td>Role</td>
                    <td>Blocked</td>
                    <td>Action</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td><c:out value="${user.login}" /></td>
                        <td><c:out value="${user.name}" /></td>
                        <td><c:out value="${user.surname}" /></td>
                        <td><c:out value="${user.role}" /></td>
                        <td><c:out value="${user.blocked}" /></td>
                        <td>
                            <c:if test="${!user.blocked && user.role != 'ADMIN'}">
                                <form method="get" action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="editUser">
                                    <input type="hidden" name="id" value="${user.id}">
                                    <input type="hidden" name="action" value="block">
                                    <input type="submit" value="Block" class="submit-btn">
                                </form>
                            </c:if>
                            <c:if test="${user.blocked && user.role != 'ADMIN'}">
                                <form method="get" action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="editUser">
                                    <input type="hidden" name="id" value="${user.id}">
                                    <input type="hidden" name="action" value="unblock">
                                    <input type="submit" value="Unblock" class="submit-btn">
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
    </table>
    </div>
</body>
</html>
