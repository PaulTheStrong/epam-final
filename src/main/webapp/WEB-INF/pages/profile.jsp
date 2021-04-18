<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.language != null ? sessionScope.language : 'ru'}" scope="session"/>
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
                            <h3><c:out value="${order.title}"/></h3>
                        </div>
                        <c:out value="${order.description}" />
                        <ul>
                            <b><fmt:message key="books.authors" bundle="${rb}"/></b>:
                            <c:forEach var="author" items="${order.authors}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/controller?command=profile&authorId=${author.id}">
                                        <c:out value="${author.name} ${author.surname}" />
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                        <b><fmt:message key="orders.status" bundle="${rb}" /></b>:
                        <c:choose>
                            <c:when test="${order.orderStatus == 'ORDERED'}"><fmt:message key="orders.status.ordered" bundle="${rb}" /></c:when>
                            <c:when test="${order.orderStatus == 'IN_HAND'}"><fmt:message key="orders.status.in_hand" bundle="${rb}" /></c:when>
                            <c:when test="${order.orderStatus == 'READ_ROOM'}"><fmt:message key="orders.status.read_room" bundle="${rb}" /></c:when>
                            <c:when test="${order.orderStatus == 'RETURNED'}"><fmt:message key="orders.status.returned" bundle="${rb}" /></c:when>
                        </c:choose>

                        <c:if test="${order.orderStatus == 'IN_HAND'}">
                            <b><fmt:formatDate value="${order.startDate}" /></b>
                            <fmt:message key="orders.status.to" bundle="${rb}"/>
                            <b><fmt:formatDate value="${order.endDate}" /></b>
                        </c:if>
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
