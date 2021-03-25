<%@ page import="by.it_academy.jd2.messenger.model.dto.User" %>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Профиль пользователя</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

</head>

<body class="d-flex flex-column h-100">
    <%@include file="header.jsp"%>

    <main class="container">
        <%
            User user = (User) request.getAttribute("user");
        %>

        <h4 class="mb-3">Профиль пользователя</h4>

        <h5 class="mt-3 mb-2 text-primary">Данные для входа</h5>

        <div class="row g-2">
            <div class="col-md-3">
                <label class="form-label">Имя пользователя</label>
                <input type="text" class="form-control" value=<%= user.getName() %> readonly>
            </div>
        </div>
        <div class="row g-2">
            <div class="col-md-3">
                <label class="form-label">Пароль</label>
                <input type="text" class="form-control" value=<%= user.getPassword() %> readonly>
            </div>
        </div>


        <h5 class="mt-3 mb-2 text-primary">Персональные данные</h5>
        <div class="row g-2 ">
            <div class="col-md-3">
                <label class="form-label">Имя пользователя</label>
                <input type="text" class="form-control" value=<%= user.getFirstName() %> readonly>
            </div>
        </div>
        <div class="row g-2">
            <div class="col-md-3">
                <label class="form-label">Фамилия</label>
                <input type="text" class="form-control" value=<%= user.getLastName() %> readonly>
            </div>
        </div>
        <div class="row g-2">
            <div class="col-md-3">
                <label class="form-label">Дата рождения</label>
                <input type="text" class="form-control" value=<%= user.getDateOfBirth() %> readonly>
            </div>
        </div>
    </main>

    <%@include file="footer.jsp"%>
</body>
</html>
