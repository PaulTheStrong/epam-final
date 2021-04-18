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
        <div class="bookmarks">
            <h2>
                <a href="${pageContext.request.contextPath}/controller?command=librarian&requestedStatus=ORDERED">
                    <fmt:message key="librarian.header.ordered" bundle="${rb}" />
                </a>
            </h2>
            <h2>
                <a href="${pageContext.request.contextPath}/controller?command=librarian&requestedStatus=IN_HAND">
                    <fmt:message key="librarian.header.handedOut" bundle="${rb}" />
                </a>
            </h2>
            <h2>
                <a href="${pageContext.request.contextPath}/controller?command=librarian&requestedStatus=READ_ROOM">
                    <fmt:message key="librarian.header.readingRoom" bundle="${rb}" />
                </a>
            </h2>
            <h2>
                <a href="${pageContext.request.contextPath}/controller?command=librarian&requestedStatus=RETURNED">
                    <fmt:message key="librarian.header.returned" bundle="${rb}" />
                </a>
            </h2>
        </div>
        <c:forEach var="order" items="${requestScope.userOrders}" >
            <div class="book">
                <div class="book-image">
                    <img src="${order.imagePath}">
                </div>
                <div class="book-description">
                    <div class="book-title">
                        <h3><c:out value="${order.title}"/></h3>
                    </div>
                    <c:out value="${order.description}" />
                    <p class="mtb5"><b><fmt:message key="orders.readerName" bundle="${rb}"/></b>: <c:out value="${order.userName}" /> <c:out value="${order.userSurname}"/></p>
                    <p class="mtb5"><b><fmt:message key="orders.status" bundle="${rb}" /></b>:
                    <c:choose>
                        <c:when test="${order.orderStatus == 'ORDERED'}"><fmt:message key="orders.status.ordered" bundle="${rb}" /></c:when>
                        <c:when test="${order.orderStatus == 'IN_HAND'}"><fmt:message key="orders.status.in_hand" bundle="${rb}" /></c:when>
                        <c:when test="${order.orderStatus == 'READ_ROOM'}"><fmt:message key="orders.status.read_room" bundle="${rb}" /></c:when>
                        <c:when test="${order.orderStatus == 'RETURNED'}"><fmt:message key="orders.status.returned" bundle="${rb}" /></c:when>
                    </c:choose>

                    <c:if test="${order.orderStatus == 'IN_HAND'}">
                        <fmt:formatDate value="${order.startDate}" />
                        <fmt:message key="orders.status.to" bundle="${rb}"/>
                        <fmt:formatDate value="${order.endDate}" />
                    </c:if>
                    </p>
                    <c:if test="${requestScope.requestedStatus == 'ORDERED'}">
                        <form method="post" class="inline-block">
                            <input type="hidden" name="command" value="librarian">
                            <input type="hidden" name="requestedStatus" value="${requestScope.requestedStatus}">
                            <input type="hidden" name="newStatus" value="IN_HAND">
                            <input type="hidden" name="orderId" value="${order.id}">
                            <input type="submit" value="<fmt:message key="librarian.handOut" bundle="${rb}" />" class="submit-btn">
                        </form>
                        <form method="post" class="inline-block">
                            <input type="hidden" name="command" value="librarian">
                            <input type="hidden" name="requestedStatus" value="${requestScope.requestedStatus}">
                            <input type="hidden" name="newStatus" value="READ_ROOM">
                            <input type="hidden" name="orderId" value="${order.id}">
                            <input type="submit" value="<fmt:message key="librarian.toReadRoom" bundle="${rb}" />" class="submit-btn">
                        </form>
                    </c:if>

                    <c:if test="${requestScope.requestedStatus == 'IN_HAND' || requestScope.requestedStatus == 'READ_ROOM'}">
                        <form method="post" class="inline-block">
                            <input type="hidden" name="command" value="librarian">
                            <input type="hidden" name="requestedStatus" value="${requestScope.requestedStatus}">
                            <input type="hidden" name="newStatus" value="RETURNED">
                            <input type="hidden" name="orderId" value="${order.id}">
                            <input type="submit" value="<fmt:message key="librarian.return" bundle="${rb}" />" class="submit-btn">
                        </form>
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
