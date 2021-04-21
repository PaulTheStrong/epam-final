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
                <a href="${pageContext.request.contextPath}/controller?command=librarian&requestedStatus=OVERDUED">
                    <fmt:message key="librarian.header.overdued" bundle="${rb}" />
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
    </div>
</div>

</body>
</html>
