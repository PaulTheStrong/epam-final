<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="pagecontent" var="rb" />

<html>
<head>
    <title>
        <fmt:message key="header.books" bundle="${rb}"/>
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
                            <a href="${pageContext.request.contextPath}/controller?command=books&author_id=${author.id}">
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
                        <a href="${pageContext.request.contextPath}/controller?command=books&genre_id=${genre.id}">
                            <c:out value="${genre.name}" />
                        </a>
                    </li>
                </c:forEach>
            </ul>
            </div>
        </div>
        <div class="books">
            <c:forEach var="book" items="${requestScope.bookList}">
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
                            <b>Жанры</b>:
                            <c:forEach var="genre" items="${book.genres}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/controller?command=books&genre_id=${genre.id}">
                                        <c:out value="${genre.name}" />
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                        <ul>
                            <b>Авторы: </b>:
                            <c:forEach var="author" items="${book.authors}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/controller?command=books&author_id=${author.id}">
                                        <c:out value="${author.name} ${author.surname}" />
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
<script src="static/script.js"></script>
</html>
