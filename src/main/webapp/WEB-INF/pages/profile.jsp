<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale.language != null ? sessionScope.locale.language : 'ru'}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb" />

<html>
<head>
    <link rel="stylesheet" href="static/style.css">
</head>

<body>
    <jsp:include page="fragments/header.jsp" />
    <div id="main" class="flex-column">
        <div class="book-orders">
            <h1>
               <fmt:message key="profile.myBooks" bundle="${rb}" />
            </h1>
            <c:forEach var="order" items="${requestScope.userOrders}" >
                <div class="book">
                    <div class="book-image">
                        <img src="${order.imagePath}" alt="">
                    </div>
                    <div class="book-description">
                        <div class="book-title">
                            <h3><c:out value="${order.titleRu}"/></h3>
                        </div>
                        <c:out value="${order.descriptionRu}" />
                        <ul>
                            <b><fmt:message key="sidebar.authors" bundle="${rb}"/></b>:
                            <c:forEach var="author" items="${order.authors}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/controller?command=profile&authorId=${author.id}">
                                        <c:out value="${author.name} ${author.surname}" />
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${requestScope.currentPage != 1}">
                <a href="${pageContext.request.contextPath}/controller?command=profile<c:if test="${requestScope.authorId != null}">&authorId=${requestScope.authorId}</c:if><c:if test="${requestScope.genreId != null}">&genreId=${requestScope.genreId}</c:if>&page=${requestScope.currentPage - 1}"><fmt:message key="books.prev" bundle="${rb}" /></a>
            </c:if>
            <span>${requestScope.currentPage}</span>
            <c:if test="${!requestScope.isLast}">
                <a href="${pageContext.request.contextPath}/controller?command=profile<c:if test="${requestScope.authorId != null}">&authorId=${requestScope.authorId}</c:if><c:if test="${requestScope.genreId != null}">&genreId=${requestScope.genreId}</c:if>&page=${requestScope.currentPage + 1}"><fmt:message key="books.next" bundle="${rb}" /></a>
            </c:if>
        </div>
    </div>

</body>
</html>
