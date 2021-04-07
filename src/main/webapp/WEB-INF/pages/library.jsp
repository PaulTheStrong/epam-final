<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.language != null ? sessionScope.language : 'ru'}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb" />
<c:set var="libraryCommand" value="library" />

<html>
<head>
    <title>
        <fmt:message key="header.library" bundle="${rb}"/>
    </title>
    <link rel="stylesheet" href="static/style.css">
</head>
<body>
    <jsp:include page="fragments/header.jsp" />
    <div id="main">
        <div class="book-search-bar">
            <div class="search-block">
            <h4>
                <fmt:message key="sidebar.authors" bundle="${rb}"/>
            </h4>
                <ul>
                    <c:forEach var="author" items="${requestScope.authorList}">
                        <li>
                            <a href="${pageContext.request.contextPath}/controller?command=${libraryCommand}&authorId=${author.id}">
                                <c:out value="${author.name} ${author.surname}" />
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="search-block">
            <h4>
                <fmt:message key="sidebar.genres" bundle="${rb}"/>
            </h4>
            <ul>
                <c:forEach var="genre" items="${requestScope.genreList}">
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=${libraryCommand}&genreId=${genre.id}">
                            <c:out value="${genre.name}" />
                        </a>
                    </li>
                </c:forEach>
            </ul>
            </div>
        </div>
        <div class="books">
            <c:forEach var="bookDto" items="${requestScope.bookList}">
                <c:set var="book" value="${bookDto.book}" />
                <div class="book">
                    <div class="book-image">
                        <img src="${book.imagePath}" alt="">
                    </div>
                    <div class="book-description">
                        <div class="book-title">
                            <h3><c:out value="${book.titleRu}"/></h3>
                        </div>
                        <c:out value="${book.descriptionRu}" />
                        <ul>
                            <b><fmt:message key="sidebar.genres" bundle="${rb}"/></b>:
                            <c:forEach var="genre" items="${bookDto.genres}" >
                                <li>
                                    <a href="${pageContext.request.contextPath}/controller?command=${libraryCommand}&genreId=${genre.id}">
                                        <c:out value="${genre.name}" />
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                        <ul>
                            <b><fmt:message key="sidebar.authors" bundle="${rb}"/></b>:
                            <c:forEach var="author" items="${bookDto.authors}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/controller?command=${libraryCommand}&authorId=${author.id}">
                                        <c:out value="${author.name} ${author.surname}" />
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${requestScope.currentPage != 1}">
                <a href="${pageContext.request.contextPath}/controller?command=${libraryCommand}<c:if test="${requestScope.authorId != null}">&authorId=${requestScope.authorId}</c:if><c:if test="${requestScope.genreId != null}">&genreId=${requestScope.genreId}</c:if>&page=${requestScope.currentPage - 1}"><fmt:message key="books.prev" bundle="${rb}" /></a>
            </c:if>
            <span>${requestScope.currentPage}</span>
            <c:if test="${!requestScope.isLast}">
            <a href="${pageContext.request.contextPath}/controller?command=${libraryCommand}<c:if test="${requestScope.authorId != null}">&authorId=${requestScope.authorId}</c:if><c:if test="${requestScope.genreId != null}">&genreId=${requestScope.genreId}</c:if>&page=${requestScope.currentPage + 1}"><fmt:message key="books.next" bundle="${rb}" /></a>
            </c:if>
        </div>
    </div>
</body>
<script src="static/script.js"></script>
</html>
