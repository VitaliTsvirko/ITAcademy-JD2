<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<%!  private static String getTicketNoun(String value) {
    int lastNumber = Integer.parseInt(value.substring(value.length() - 1));
    if (lastNumber == 1) {
        return "билет";
    }
    if (lastNumber >= 2 && lastNumber <= 4) {
        return "билета";
    }
    if (lastNumber >= 5 && lastNumber <= 9 || lastNumber == 0) {
        return "билетов";
    }
    return "";
    }
%>

<html>
<head>
    <title>Аэропорты - Фильтр</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <c:set var="queryString" value="${pageContext.request.contextPath}/search?"/>
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
                <input type="date" name="departureDate" id="departureDate" class="form-control input-block" placeholder="Дата вылета" autofocus >
            </div>

            <div class="col">
                <label for="departureAirport" class="sr-only">Аэропорт вылета</label>
                <p><select class="form-select" name="departureAirport" id="departureAirport" aria-label="Аэропорт вылета" required>
                    <c:forEach items="${requestScope.airportsMap}"
                               var="airport">
                        <c:choose>
                            <c:when test="${airport.key.equalsIgnoreCase(\"DME\")}">
                                <option selected value="${airport.key}">${airport.value}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${airport.key}">${airport.value}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select></p>
            </div>

            <div class="col">
                <label for="arrivalDate" class="sr-only">Дата прилета</label>
                <input type="date" name="arrivalDate" id="arrivalDate" class="form-control input-block" placeholder="Дата вылета">
            </div>

            <div class="col">
                <label for="arrivalAirport" class="sr-only">Аэропорт прилета</label>
                <p><select class="form-select" name="arrivalAirport" id="arrivalAirport" aria-label="Аэропорт прилета" required>
                    <c:forEach items="${requestScope.airportsMap}"
                               var="airport">
                        <c:choose>
                            <c:when test="${airport.key.equalsIgnoreCase(\"ROV\")}">
                                <option selected value="${airport.key}">${airport.value}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${airport.key}">${airport.value}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select></p>
            </div>
            <div class="col-1" style="padding-top: 23px;">
                <button class="btn btn btn-success" type="submit">Поиск</button>
            </div>
        </div>
    </form>

    <c:if test="${requestScope.ticketsData != null}">
        <div class="row">
            <div class="col" style=" height: 650px; overflow-y: scroll;">
                <table class="table table-hover table-responsive">
                    <thead>
                    <tr>
                        <th scope="col">flight_no</th>
                        <th scope="col">departure_airport</th>
                        <th scope="col">arrival_airport</th>
                        <th scope="col">scheduled_departure</th>
                        <th scope="col">scheduled_arrival</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.ticketsData}"
                               var="data">
                        <tr>
                            <td>${data.flight_no}</td>
                            <td>${data.departure_airport}</td>
                            <td>${data.arrival_airport}</td>
                            <td>${data.scheduled_departure}</td>
                            <td>${data.scheduled_arrival}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="row align-items-center">
            <div class="col">
                <span class="align-middle">Найдено ${requestScope.ticketsTotalCount} <%=getTicketNoun(request.getAttribute("ticketsTotalCount").toString())%> </span>
            </div>

            <div class="col d-flex justify-content-end">
                <ul class="pagination justify-content-right">
                    <c:choose>
                        <c:when test="${empty requestScope.pageNo or requestScope.pageNo == 1}">
                            <li class="page-item disabled"><a class="page-link" href="#">Назад</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="${queryString}&pageNo=${requestScope.pageNo-1}">Назад</a></li>
                        </c:otherwise>
                    </c:choose>

                    <li class="page-item">
                        <span class="page-link"> <c:choose>
                                <c:when test="${empty requestScope.pageNo}"> 1 </c:when>
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
                            <li class="page-item"><a class="page-link" href="${queryString}&pageNo=${requestScope.pageNo+1}">Вперед</a></li>
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