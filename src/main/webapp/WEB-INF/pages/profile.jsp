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
    <div id="main">
        <div class="book-orders">
            <h1>
               <fmt:message key="profile.my-books" bundle="${rb}" />
            </h1>
            <c:forEach var="order" items="${requestScope.userOrders}" >
            <li>
                ${order.bookDto.book.id}
            </li>
            </c:forEach>
        </div>
    </div>

</body>
</html>
