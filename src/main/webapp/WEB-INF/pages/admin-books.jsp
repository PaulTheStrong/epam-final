<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language != null ? sessionScope.language : 'ru'}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb" />
<html>
<head>
    <title>Admin books</title>
    <link href="static/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="fragments/header.jsp" />
<div id="main" class="flex-column">
    <div class="bookmarks">
        <h2>
            <a href="${pageContext.request.contextPath}/controller?command=admin&edit=books">Books</a>
        </h2>
        <h2>
            <a href="${pageContext.request.contextPath}/controller?command=admin&edit=users">Users</a>
        </h2>
    </div>
    <div class="full-width">
        <c:if test="${requestScope.orderedBook != null}">
            <div class="success-box" >
                <fmt:message key="success.orderBookStart" bundle="${rb}" /> ${requestScope.orderedBook.title} <fmt:message key="success.orderBookEnd" bundle="${rb}"/>
            </div>
        </c:if>
        <c:forEach var="book" items="${requestScope.bookList}">
            <div class="book">
                <div class="book-image">
                    <img src="${book.imagePath}" alt="">
                </div>
                <div class="book-description">
                    <div class="book-title">
                        <h3><c:out value="${book.title}"/></h3>
                    </div>
                    <c:out value="${book.description}" />
                    <ul>
                        <b><fmt:message key="books.genres" bundle="${rb}"/></b>:
                        <c:forEach var="genre" items="${book.genres}" >
                            <li>
                                <c:out value="${genre.name}" />
                            </li>
                        </c:forEach>
                    </ul>
                    <ul>
                        <b><fmt:message key="books.authors" bundle="${rb}"/></b>:
                        <c:forEach var="author" items="${book.authors}">
                            <li>
                                <c:out value="${author.name} ${author.surname}" />
                            </li>
                        </c:forEach>
                    </ul>
                    <p><fmt:message key="books.quantity" bundle="${rb}"/>: <c:out value="${book.quantity}"/></p>
                    <form method="get">
                        <input type="hidden" name="command" value="editBook" />
                        <input type="hidden" name="bookId" value="${book.id}" />
                        <input type="submit" value="<fmt:message bundle="${rb}" key="admin.edit" />" class="submit-btn"/>
                    </form>
                </div>
            </div>
        </c:forEach>
        <form method="get">
            <input type="hidden" name="command" value="editBook">
            <input type="hidden" name="bookId" value="0"/>
            <input type="submit" value="admin.add">
        </form>
    </div>
</div>
</body>
</html>
