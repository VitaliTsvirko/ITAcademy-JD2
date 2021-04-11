<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="search" method="GET">
    <div class="row">
        <div class="col">
            <label for="departureDate" class="sr-only">Дата вылета</label>
            <input type="date" value="${requestScope.searchParam.departureDate}" name="departureDate" id="departureDate" class="form-control input-block" placeholder="Дата вылета" >
        </div>

        <div class="col">
            <label for="departureAirport" class="sr-only">Аэропорт вылета</label>
            <p><select class="form-select" name="departureAirport" id="departureAirport" aria-label="Аэропорт вылета" required>
                <c:forEach items="${requestScope.airportsMap}" var="airport">
                        <option ${airport.key.equalsIgnoreCase(requestScope.searchParam.departureAirport) ? "selected" : ""} value="${airport.key}">${airport.value}</option>
                </c:forEach>
            </select></p>
        </div>

        <div class="col">
            <label for="arrivalDate" class="sr-only">Дата прилета</label>
            <input type="date" value="${requestScope.searchParam.arrivalDate}" name="arrivalDate" id="arrivalDate" class="form-control input-block" placeholder="Дата вылета" >
        </div>

        <div class="col">
            <label for="arrivalAirport" class="sr-only">Аэропорт прилета</label>
            <p><select class="form-select" name="arrivalAirport" id="arrivalAirport" aria-label="Аэропорт прилета" required>
                <c:forEach items="${requestScope.airportsMap}" var="airport">
                    <option ${airport.key.equalsIgnoreCase(requestScope.searchParam.arrivalAirport) ? "selected" : ""} value="${airport.key}">${airport.value}</option>
                </c:forEach>
            </select></p>
        </div>
        <div class="col-1" style="padding-top: 23px;">
            <button class="btn btn btn-success" type="submit">Поиск</button>
        </div>
    </div>
</form>
