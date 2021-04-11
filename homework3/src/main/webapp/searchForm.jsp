<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="search" method="GET">
    <div class="row">
        <div class="col">
            <label for="departureDate" class="sr-only">Дата вылета</label>
                <c:choose>
                    <c:when test="${empty requestScope.departureDate}">
                        <input type="date" name="departureDate" id="departureDate" class="form-control input-block" placeholder="Дата вылета" >
                    </c:when>
                    <c:otherwise>
                        <input type="date" value="${requestScope.departureDate}" name="departureDate" id="departureDate" class="form-control input-block" placeholder="Дата вылета" >
                    </c:otherwise>
                </c:choose>
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
            <c:choose>
                <c:when test="${empty requestScope.departureDate}">
                    <input type="date" name="arrivalDate" id="arrivalDate" class="form-control input-block" placeholder="Дата вылета">
                </c:when>
                <c:otherwise>
                    <input type="date" value="${requestScope.arrivalDate}" name="arrivalDate" id="arrivalDate" class="form-control input-block" placeholder="Дата вылета" >
                </c:otherwise>
            </c:choose>
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
