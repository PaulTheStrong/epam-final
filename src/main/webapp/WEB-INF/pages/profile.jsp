<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="pagecontent" var="rb" />

<html>
<head>
    <link rel="stylesheet" href="static/style.css">
</head>

<body>
    <jsp:include page="fragments/header.jsp" />
    <div id="main">
        <h1 class="profile-by-books">
           <fmt:message key="profile.my-books" bundle="${rb}" />
        </h1>

        <c:forEach var="order" items="${requestScope.userOrders}" >
            <li>
                <h1>${order.bookId}</h1>
            </li>
        </c:forEach>
    </div>

</body>
</html>
