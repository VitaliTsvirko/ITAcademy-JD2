<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Аэропорты</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
</head>

<body>
<%@include file="header.jsp"%>

<main class="container">
    <table class="table table-hover table-responsive">
        <thead>
        <tr>
            <th scope="col">airport_code</th>
            <th scope="col">airport_name</th>
            <th scope="col">city</th>
            <th scope="col">coordinates</th>
            <th scope="col">timezone</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.allAirportsData}"
                       var="data">
                <tr>
                    <td>${data.airportCode}</td>
                    <td>${data.airportName}</td>
                    <td>${data.city}</td>
                    <td><a target="_blank" href="http://maps.google.com/maps?q=${data.coordinates.y}, ${data.coordinates.x}">
                            ${data.coordinates.x}, <br> ${data.coordinates.y}</a>
                    </td>
                    <td>${data.timezone}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</main>

<%@include file="footer.jsp"%>
</body>

</html>
