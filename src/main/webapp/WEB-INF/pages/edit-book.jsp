<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language != null ? sessionScope.language : 'ru'}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb" />

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="static/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>
    <div id="main">
        <c:if test="${requestScope.bookNotFoundError == null}">
        <c:set  value="${requestScope.book}" var="book" />
        <form method="post" class="login-form" enctype="multipart/form-data">
            <input type="hidden" name="command" value="bookEdit">

            <div class="mb15">
                <c:if test="${requestScope.titleError != null}">
                    <div class="error-box"><fmt:message key="${requestScope.titleError}" bundle="${rb}" /></div>
                </c:if>
                <label for="title"><fmt:message key="edit.book.title" bundle="${rb}" /></label>
                <input type="text" name="title" value="${book.title}" id="title" class="input-field mt5">
            </div>

            <div class="mb15">
                <c:if test="${requestScope.descriptionError != null}">
                    <div class="error-box"><fmt:message key="${requestScope.descriptionError}" bundle="${rb}" /></div>
                </c:if>
                <label for="description"><fmt:message key="edit.book.description" bundle="${rb}" /></label>
                <textarea id="description" maxlength="1024" name="description">${book.description}</textarea>
            </div>

            <div class="mb15">
                <c:if test="${requestScope.quantityError != null}">
                    <div class="error-box"><fmt:message key="${requestScope.quantityError}" bundle="${rb}" /></div>
                </c:if>
                <label for="quantity"><fmt:message key="edit.book.quantity" bundle="${rb}" /></label>
                <p><input type="number" name="quantity" value="${book.quantity}" id="quantity" class="input-field-half mt5" min="0" ></p>
            </div>

            <div class="mb15" id="authors">
                <label class="mtb0"><fmt:message key="edit.book.authors" bundle="${rb}" /></label>
                <c:if test="${requestScope.authorError != null}">
                    <div class="error-box"><fmt:message key="${requestScope.authorError}" bundle="${rb}" /></div>
                </c:if>
                <c:forEach var="author" items="${book.authors}">
                    <div class="columns mt5 bm10">
                        <input type="text" name="name" value="${author.name}" class="input-field-half mt5" maxlength="50">
                        <input type="text" name="surname" value="${author.surname}" class="input-field-half mt5" maxlength="50">
                    </div>
                </c:forEach>
            </div>
            <button type="button" class="submit-btn mtb5" id="addAuthor"><fmt:message key="edit.add" bundle="${rb}"/></button>

            <div class="mb15" id="genres">
            <label class="mtb0"><fmt:message key="edit.book.genres" bundle="${rb}" /></label>
                <c:forEach var="genre" items="${book.genres}">
                    <input type="text" name="genre" value="${genre.name}" class="input-field mt5">
                </c:forEach>
            </div>
            <p><button type="button" id="addGenre" class="submit-btn mtb5"><fmt:message key="edit.add" bundle="${rb}"/></button></p>

            <div class="mb15">
                <label class="mtb0"><fmt:message key="edit.book.image" bundle="${rb}" /></label>
                <input type="hidden" name="previousPath" value="${book.imagePath}">
                <input type="file" name=image value="${book.imagePath}" class="input-field mtb5">
                <img src="${book.imagePath}" width="200px"/>
            </div>

            <input type="submit" class="submit-btn" value="<fmt:message key="edit.book.update" bundle="${rb}" />"/>
        </form>
        </c:if>
        <c:if test="${requestScope.bookNotFoundError != null}">
            <div class="error-box">
                <fmt:message key="error.bookNotFound" bundle="${rb}" />
            </div>
        </c:if>
    </div>
</body>

<script>
    function addAuthorField() {
        $('#authors').append("<div class=\"columns mt5 mb10\">" +
            "<input type=\"text\" name=\"name\" class=\"input-field-half mt5\" maxlength='50'>"+
            "<input type=\"text\" name=\"surname\"  class=\"input-field-half mt5\" maxlength='50'>" +
            "</div>");
    }

    function addGenreField() {
        $('#genres').append("<input type=\"text\" name=\"genre\" class=\"input-field mt5\" maxlength='50'>");
    }
    document.getElementById('addAuthor').onclick = addAuthorField;
    document.getElementById('addGenre').onclick = addGenreField;
</script>
</html>
