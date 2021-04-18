<%@ page contentType="text/html;charset=cp1251" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.language != null ? sessionScope.language : 'ru'}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb" />

<html>
<head>
    <link rel="stylesheet" href="static/style.css">
    <title>Register</title>
</head>
<body>

<jsp:include page="fragments/header.jsp" />
<div id="main">
    <form method="post" class="login-form">
        <div class="mtb5">
            <label class="mtb0">Логин</label>
            <input type="text" class="input-field mt5" name="login" value="${requestScope.user.login}"/>
            <c:if test="${requestScope.loginError != null}">
            <div class="error-box">
                <c:out value="${requestScope.loginError}"/>
            </div>
            </c:if>
        </div>
        <div class="mtb5">
            <label class="mtb0">Имя</label>
            <input type="text" class="input-field mt5" name="name" value="${requestScope.user.name}"/>
            <c:if test="${requestScope.nameError != null}">
            <div class="error-box">
                <c:out value="${requestScope.nameError}"/>
            </div>
            </c:if>
        </div>
        <div class="mtb5">
            <label class="mtb0">Фамилия</label>
            <input type="text" class="input-field mt5" name="surname" value="${requestScope.user.surname}" placeholder=""/>
            <c:if test="${requestScope.surnameError != null}">
            <div class="error-box">
                <c:out value="${requestScope.surnameError}"/>
            </div>
            </c:if>
        </div>
        <div class="mtb5">
            <label  class="mtb0">Пароль</label>
            <input type="password" class="input-field mt5" name="password" />
            <c:if test="${requestScope.passwordError != null}">
            <div class="error-box">
                <c:out value="${requestScope.passwordError}"/>
            </div>
            </c:if>
        </div>
        <div class="mtb5">
            <label class="mtb0">Подтверждения пароля</label>
            <input type="password" class="input-field mt5" name="password-confirm" />
        </div>
        <input type="hidden" name="command" value="register"/>
        <button type="submit" class="submit-btn">Регистрация</button>
    </form>
</div>
</body>
</html>
