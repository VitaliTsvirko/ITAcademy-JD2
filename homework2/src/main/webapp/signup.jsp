<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
     <title>SingUp</title>
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
            <h1>Регистрация нового пользователя</h1>
            <%
                if (request.getAttribute("signup-result") != null){
                    out.println("<p class=\"text-danger\">");
                    out.println(request.getAttribute("signup-result"));
                    out.println("</p>");
                }
            %>
        </div>

        <div class="auth-form-body mt-3">
            <form action="signup" method="POST" class="form-group required">

                <label for="inputName" class="sr-only">Имя пользователя</label>
                <input type="text" name="name" id="inputName" class="form-control input-block" placeholder="Имя пользователя" required autofocus >

                <label class="sr-only">Пароль</label>
                <input type="password" name="password" placeholder="Пароль" class="form-control input-block" required>

                <label class="sr-only">Имя</label>
                <input type="text" name="firstname" placeholder="Имя" class="form-control input-block" required>

                <label class="sr-only">Фамилия</label>
                <input type="text" name="lastname" placeholder="Фамилия" class="form-control input-block" required>

                <label class="sr-only">Дата рождения</label>
                <input type="date" name="dateOfBirth" placeholder="Дата рождения" class="form-control input-block" required>

                <button class="btn btn-primary btn-block" type="submit">Зарегистрироваться</button>
            </form>
        </div>

    </div>



    </main>

    <%@include file="footer.jsp"%>
 </body>

</html>


