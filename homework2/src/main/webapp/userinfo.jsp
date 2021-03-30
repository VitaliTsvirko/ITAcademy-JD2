<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Профиль пользователя</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <link href="static/css/userprofile.css" rel="stylesheet">

</head>

<body class="d-flex flex-column h-100">
    <%@include file="header.jsp"%>

    <main class="container">
        <div class="row">
            <div class="up-form-body col-sm-3">
                <div class="up-form-header">
                    <h3>Данные для входа</h3>
                </div>



                <div class="col">
                    <label class="form-label">Имя пользователя</label>
                    <input type="text" class="form-control" value= ${sessionScope.user.name} readonly>
                </div>
                <div class="col">
                    <label class="form-label">Пароль</label>
                    <input type="text" class="form-control" value= ${sessionScope.user.password} readonly>
                </div>
            </div>

            <div class="up-form-body col-sm-3">
                <div class="up-form-header">
                    <h3>Персональные данные</h3>
                </div>
                <div class="col">
                    <label class="form-label">Имя</label>
                    <input type="text" class="form-control" value= ${sessionScope.user.firstName} readonly>
                </div>
                <div class="col">
                    <label class="form-label">Фамилия</label>
                    <input type="text" class="form-control" value= ${sessionScope.user.lastName} readonly>
                </div>
                <div class="col">
                    <label class="form-label">Дата рождения</label>
                    <c:choose>
                        <c:when test="${sessionScope.user.dateOfBirth == null}">
                            <input type="text" class="form-control" value= "" readonly>
                        </c:when>
                        <c:otherwise>
                            <input type="date" class="form-control" value= ${sessionScope.user.dateOfBirth} readonly>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

    <%@include file="footer.jsp"%>
</body>
</html>
