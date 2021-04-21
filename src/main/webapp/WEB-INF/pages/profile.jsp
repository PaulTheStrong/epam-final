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
                    <a href="${pageContext.request.contextPath}/controller?command=profile&requestedStatus=ORDERED">
                        <fmt:message key="librarian.header.ordered" bundle="${rb}" />
                    </a>
                </h2>
                <h2>
                    <a href="${pageContext.request.contextPath}/controller?command=profile&requestedStatus=IN_HAND">
                        <fmt:message key="librarian.header.handedOut" bundle="${rb}" />
                    </a>
                </h2>
                <h2>
                    <a href="${pageContext.request.contextPath}/controller?command=profile&requestedStatus=READ_ROOM">
                        <fmt:message key="librarian.header.readingRoom" bundle="${rb}" />
                    </a>
                </h2>
                <h2>
                    <a href="${pageContext.request.contextPath}/controller?command=profile&requestedStatus=OVERDUED">
                        <fmt:message key="librarian.header.overdued" bundle="${rb}" />
                    </a>
                </h2>
            </div>
            <c:if test="${requestScope.successMessage != null}">
                <div class="success-box">
                <fmt:message key="${requestScope.successMessage}" bundle="${rb}"/>
                </div>
            </c:if>
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
                            <c:when test="${order.orderStatus == 'OVERDUED'}"><fmt:message key="orders.status.in_hand" bundle="${rb}" /></c:when>
                        </c:choose>

                        <c:if test="${order.orderStatus == 'IN_HAND' || order.orderStatus == 'OVERDUED'}">
                            <span style="<c:if test="${order.orderStatus == 'OVERDUED'}">color:red</c:if>">
                                <b><fmt:formatDate value="${order.startDate}" /></b>
                                <fmt:message key="orders.status.to" bundle="${rb}"/>
                                <b><fmt:formatDate value="${order.endDate}" /></b>
                                <c:if test="${order.orderStatus == 'OVERDUED'}"><fmt:message key="orders.status.overdued" bundle="${rb}" /></c:if>
                            </span>
                        </c:if>
                        <c:if test="${order.orderStatus == 'ORDERED'}">
                            <form class="inline-block" method="get">
                                <input type="hidden" name="command" value="cancelOrder">
                                <input type="hidden" name="orderId" value="${order.id}">
                                <input class="submit-btn" type="submit" onclick="return confirm('<fmt:message key="orders.cancel.confirm" bundle="${rb}"/>')" value="<fmt:message key="orders.cancel" bundle="${rb}" />">
                            </form>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</body>
</html>
