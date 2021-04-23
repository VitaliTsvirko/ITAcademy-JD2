<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Аэропорты</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <c:set var="contextPath" value="${pageContext.request.contextPath}/"/>
</head>

<body class="d-flex flex-column h-100">
<%@include file="header.jsp"%>

<main class="container">

    <c:choose>
        <c:when test="${requestScope.error}">
            <div class="row">
                <div class="col">
                    <span class="align-middle text-danger">${requestScope.errorMessage}</span>
                </div>
            </div>
        </c:when>

        <c:otherwise>
            <table class="table table-hover table-responsive">
                <thead>
                <tr>
                    <th scope="col">Код аэропорта</th>
                    <th scope="col">Название аэропорта</th>
                    <th scope="col">Город</th>
                    <th scope="col">Координаты</th>
                    <th scope="col">Часовой пояс</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.allAirportsData}"
                           var="data">
                    <tr>
                        <td>${data.airportCode}</td>
                        <td>${data.airportName}</td>
                        <td>${data.city}</td>
<%--                        <td><a target="_blank" href="http://maps.google.com/maps?q=${data.coordinates.y}, ${data.coordinates.x}">
                                ${data.coordinates.x}, <br> ${data.coordinates.y}</a>
                        </td>--%>
                        <td>${data.coordinates}</td>
                        <td>${data.timezone}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

</main>

</body>

</html>
