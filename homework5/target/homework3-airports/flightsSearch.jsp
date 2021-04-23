<%@ page import="by.it_academy.jd2.airports.core.dto.FlightsPageParam" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%!  private static String getFlightNoun(String value) {
    int lastNumber = Integer.parseInt(value.substring(value.length() - 1));
    if (lastNumber == 1) {
        return "рейс";
    }
    if (lastNumber >= 2 && lastNumber <= 4) {
        return "рейса";
    }
    if (lastNumber >= 5 && lastNumber <= 9 || lastNumber == 0) {
        return "рейсов";
    }
    return "";
    }
%>

<html>
<head>
    <title>Рейсы</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
</head>

<body class="d-flex flex-column h-100">
<%@include file="header.jsp"%>

<main class="container">
    <%@include file="searchForm.jsp"%>

    <c:if test="${requestScope.error}">
        <div class="row">
            <div class="col">
                <span class="align-middle text-danger">${requestScope.errorMessage}</span>
            </div>
        </div>
    </c:if>

    <c:if test="${requestScope.flightsData == null and requestScope.pageParam != null}">
        <div class="row">
            <div class="col">
                <span class="align-middle">По заданным критериям рейсов не найдено</span>
            </div>
        </div>
    </c:if>

    <c:if test="${requestScope.flightsData != null}">
        <div class="row">
            <div class="col">
                <span class="align-middle">Найдено ${requestScope.pageParam.flightsTotalCount} <%=getFlightNoun(((FlightsPageParam) request.getAttribute("pageParam")).getFlightsTotalCount().toString())%> </span>
            </div>
        </div>

        <div class="row">
            <div class="col" style=" height: 650px; overflow-y: scroll;">
                <table class="table table-hover table-responsive">
                    <thead>
                    <tr>
                        <th scope="col">Номер рейса</th>
                        <th scope="col">Расписание вылета</th>
                        <th scope="col">Расписание прилета</th>
                        <th scope="col">Актуальное время вылета</th>
                        <th scope="col">Актуальное время прилета</th>
                        <th scope="col">Модель самолета</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.flightsData}"
                               var="data">
                        <tr>
                            <td>${data.flight_no}</td>
                            <fmt:parseDate value="${data.scheduled_departure}" pattern="yyyy-MM-dd'T'HH:mm'Z'" var="scheduled_departure_parsed" type="both" />
                            <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${scheduled_departure_parsed}" /></td>

                            <fmt:parseDate value="${data.scheduled_arrival}" pattern="yyyy-MM-dd'T'HH:mm'Z'" var="scheduled_arrival_parsed" type="both" />
                            <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${scheduled_arrival_parsed}" /></td>

                            <fmt:parseDate value="${data.actual_departure}" pattern="yyyy-MM-dd'T'HH:mm'Z'" var="actual_departure_parsed" type="both" />
                            <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${actual_departure_parsed}" /></td>

                            <fmt:parseDate value="${data.actual_arrival}" pattern="yyyy-MM-dd'T'HH:mm'Z'" var="actual_arrival_parsed" type="both" />
                            <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${actual_arrival_parsed}" /></td>

                            <td>${data.aircraft_model}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="row align-items-center">
            <div class="col d-flex justify-content-end">
                <ul class="pagination justify-content-right">
                    <c:choose>
                        <c:when test="${requestScope.pageParam.pageNo == 1}">
                            <li class="page-item disabled"><a class="page-link" href="#">Назад</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/search?${requestScope.queryString}&pageNo=${requestScope.pageParam.pageNo-1}">Назад</a></li>
                        </c:otherwise>
                    </c:choose>

                    <li class="page-item">
                        <span class="page-link">  ${requestScope.pageParam.pageNo} из ${requestScope.pageParam.totalPages} </span>
                    </li>

                    <c:choose>
                        <c:when test="${requestScope.pageParam.pageNo >= requestScope.pageParam.totalPages}">
                            <li class="page-item disabled"><a class="page-link" href="#">Вперед</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/search?${requestScope.queryString}&pageNo=${requestScope.pageParam.pageNo+1}">Вперед</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </c:if>
</main>

</body>
</html>
