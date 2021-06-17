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
                <a href="${pageContext.request.contextPath}/controller?command=admin&edit=books">
                    <fmt:message bundle="${rb}" key="edit.users.header.books" />
                </a>
            </h2>
            <h2>
                <a href="${pageContext.request.contextPath}/controller?command=editUser">
                    <fmt:message bundle="${rb}" key="edit.users.header.users" />
                </a>
            </h2>
        </div>
        <table class="full-width table">
            <thead>
                <tr>
                    <td>
                        <fmt:message bundle="${rb}" key="edit.users.table.login" />
                    </td>
                    <td>
                        <fmt:message bundle="${rb}" key="edit.users.table.name" />
                    </td>
                    <td>
                        <fmt:message bundle="${rb}" key="edit.users.table.surname" />
                    </td>
                    <td>
                        <fmt:message bundle="${rb}" key="edit.users.table.role" />
                    </td>
                    <td>
                        <fmt:message bundle="${rb}" key="edit.users.table.blocked" />
                    </td>
                    <td>
                        <fmt:message bundle="${rb}" key="edit.users.table.action" />
                    </td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${requestScope.users}">
                    <tr>
                        <td><c:out value="${user.login}" /></td>
                        <td><c:out value="${user.name}" /></td>
                        <td><c:out value="${user.surname}" /></td>
                        <td><fmt:message bundle="${rb}" key="edit.users.table.role.${user.role}"/></td>
                        <td><fmt:message bundle="${rb}" key="edit.users.table.blocked.${user.blocked}"/></td>
                        <td>
                            <c:if test="${!user.blocked && user.role != 'ADMIN'}">
                                <form method="get" action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="editUser">
                                    <input type="hidden" name="id" value="${user.id}">
                                    <input type="hidden" name="action" value="block">
                                    <input type="submit" value="<fmt:message bundle="${rb}" key="edit.users.table.action.block"/>" class="submit-btn">
                                </form>
                            </c:if>
                            <c:if test="${user.blocked && user.role != 'ADMIN'}">
                                <form method="get" action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="editUser">
                                    <input type="hidden" name="id" value="${user.id}">
                                    <input type="hidden" name="action" value="unblock">
                                    <input type="submit" value="<fmt:message bundle="${rb}" key="edit.users.table.action.block"/>" class="submit-btn">
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
