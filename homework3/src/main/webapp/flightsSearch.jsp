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

    <c:set var="contextPath" value="${pageContext.request.contextPath}/search"/>

    <c:set var="queryString" value="?"/>
    <c:if test="${not empty requestScope.departureDate}">
        <c:set var="queryString" value="${queryString}&departureDate=${requestScope.departureDate}"/>
    </c:if>
    <c:if test="${not empty requestScope.departureAirport}">
        <c:set var="queryString" value="${queryString}&departureAirport=${requestScope.departureAirport}"/>
    </c:if>
    <c:if test="${not empty requestScope.arrivalDate}">
        <c:set var="queryString" value="${queryString}&arrivalDate=${requestScope.arrivalDate}"/>
    </c:if>
    <c:if test="${not empty requestScope.arrivalAirport}">
        <c:set var="queryString" value="${queryString}&arrivalAirport=${requestScope.arrivalAirport}"/>
    </c:if>
</head>

<body>
<%@include file="header.jsp"%>

<main class="container">
    <form action="search" method="GET">
        <div class="row">
            <div class="col">
                <label for="departureDate" class="sr-only">Дата вылета</label>
                <input type="date" name="departureDate" id="departureDate" class="form-control input-block" placeholder="Дата вылета" >
            </div>

            <div class="col">
                <label for="departureAirport" class="sr-only">Аэропорт вылета</label>
                <p><select class="form-select" name="departureAirport" id="departureAirport" aria-label="Аэропорт вылета" required>
                    <c:choose>
                        <c:when test="${empty requestScope.departureAirport}">
                            <c:forEach items="${requestScope.airportsMap}"
                                       var="airport">
                                <option value="${airport.key}">${airport.value}</option>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${requestScope.airportsMap}"
                                       var="airport">
                                <c:choose>
                                    <c:when test="${airport.key.equalsIgnoreCase(requestScope.departureAirport)}">
                                        <option selected value="${airport.key}">${airport.value}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${airport.key}">${airport.value}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </select></p>
            </div>

            <div class="col">
                <label for="arrivalDate" class="sr-only">Дата прилета</label>
                <input type="date" name="arrivalDate" id="arrivalDate" class="form-control input-block" placeholder="Дата вылета">
            </div>

            <div class="col">
                <label for="arrivalAirport" class="sr-only">Аэропорт прилета</label>
                <p><select class="form-select" name="arrivalAirport" id="arrivalAirport" aria-label="Аэропорт прилета" required>
                    <c:choose>
                        <c:when test="${empty requestScope.arrivalAirport}">
                            <c:forEach items="${requestScope.airportsMap}"
                                       var="airport">
                                <option value="${airport.key}">${airport.value}</option>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${requestScope.airportsMap}"
                                       var="airport">
                                <c:choose>
                                    <c:when test="${airport.key.equalsIgnoreCase(requestScope.arrivalAirport)}">
                                        <option selected value="${airport.key}">${airport.value}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${airport.key}">${airport.value}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </select></p>
            </div>
            <div class="col-1" style="padding-top: 23px;">
                <button class="btn btn btn-success" type="submit">Поиск</button>
            </div>
        </div>
    </form>

    <c:if test="${requestScope.flightsData == null and not empty requestScope.departureAirport}">
        <div class="row">
            <div class="col">
                <span class="align-middle">По заданным критериям рейсов не найдено</span>
            </div>
        </div>
    </c:if>


    <c:if test="${requestScope.flightsData != null}">
        <div class="row">
            <div class="col">
                <span class="align-middle">Найдено ${requestScope.flightsTotalCount} <%=getFlightNoun(request.getAttribute("flightsTotalCount").toString())%> </span>
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
                            <td>${data.scheduled_departure}</td>
                            <td>${data.scheduled_arrival}</td>
                            <td>${data.actual_departure}</td>
                            <td>${data.actual_arrival}</td>
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
                        <c:when test="${empty requestScope.pageNo or requestScope.pageNo == 1}">
                            <li class="page-item disabled"><a class="page-link" href="#">Назад</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="$${contextPath}${queryString}&pageNo=${requestScope.pageNo-1}">Назад</a></li>
                        </c:otherwise>
                    </c:choose>

                    <li class="page-item">
                        <span class="page-link"> <c:choose>
                                <c:when test="${empty requestScope.pageNo or requestScope.pageNo <= 0}"> 1 </c:when>
                                <c:otherwise>
                                    ${requestScope.pageNo}
                                </c:otherwise>
                            </c:choose>
                            из ${requestScope.totalPages}
                        </span>
                    </li>

                    <c:choose>
                        <c:when test="${requestScope.pageNo == requestScope.totalPages}">
                            <li class="page-item disabled"><a class="page-link" href="#">Вперед</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="${contextPath}${queryString}&pageNo=${requestScope.pageNo+1}">Вперед</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </c:if>

</main>

<%@include file="footer.jsp"%>
</body>

</html>
