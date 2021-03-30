<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
 <head>
   <title>Авторизация</title>
     <meta charset="utf-8">
     <meta name="viewport" content="width=device-width, initial-scale=1">
     <meta name="description" content="">
 </head>

 <body>
    <%@include file="header.jsp"%>

    <link href="static/css/auth.css" rel="stylesheet">

    <main class="container">
        <div class="auth-form px-3">
            <div class="auth-form-header p-0">
                <h1>Для использования мессенджера <br> авторизуйтесь</h1>
                <c:if test="${requestScope.loginResult != null}">
                    <p class="text-danger">${requestScope.loginResult}</p>
                </c:if>
            </div>

            <div class="auth-form-body mt-3">
                <form action="login" method="POST" class="form-signin">

                    <label for="inputName" class="sr-only">Имя пользователя</label>
                    <input type="text" name="name" id="inputName" class="form-control input-block" placeholder="Имя пользователя" required autofocus >

                    <label for="inputPassword" class="sr-only">Пароль</label>
                    <input type="password" name="password" id="inputPassword" class="form-control input-block" placeholder="Пароль" required>

                    <button class="btn btn-primary btn-block" type="submit">Войти</button>
                </form>
            </div>

            <p class="login-callout mt-3"> Нет аккаунта, <a href="${pageContext.request.contextPath}/signup.jsp">зарегистрируйтесь</a> </p>
        </div>
    </main>

    <%@include file="footer.jsp"%>
 </body>

</html>


