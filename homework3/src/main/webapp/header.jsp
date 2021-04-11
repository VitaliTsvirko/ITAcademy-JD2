<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css" integrity="sha384-DhY6onE6f3zzKbjUPRc2hOzGAdEf4/Dz+WJwBvEYL/lkkIsI3ihufq9hk9K4lVoK" crossorigin="anonymous">
<link href="static/css/navbar.css" rel="stylesheet">

<header>
    <c:choose>
        <c:when test="${not empty queryString}">
            <c:set var="pageURL" value="${contextPath}${queryString}"/>
        </c:when>
        <c:otherwise>
            <c:set var="pageURL" value="${contextPath}"/>
        </c:otherwise>
    </c:choose>

  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-xxl">
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}">Аэропорты</a>
                </li>
                <li class="nav-item">
                   <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/search">Поиск билетов</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/homework3-airports/search?departureDate=&departureAirport=DME&arrivalDate=&arrivalAirport=ROV">Поиск билетов</a>
                </li>
            </ul>

            <ul class="navbar-nav ml-auto">
                <li>
                    <form action="lang" method="POST">
                        <c:choose>
                            <c:when test="${sessionScope.lang.textCode.equalsIgnoreCase(\"en\")}">
                                <input type="hidden" name="pageURL" value="${pageURL}">
                                <input type="submit" class="btn btn-outline-secondary" name="lang" value="Ru">
                                <input type="submit" class="btn btn-outline-primary" name="lang" value="En">
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="pageURL" value="${pageURL}">
                                <input type="submit" class="btn btn-outline-primary" name="lang" value="Ru">
                                <input type="submit" class="btn btn-outline-secondary" name="lang" value="En">
                            </c:otherwise>
                        </c:choose>
                    </form>
                </li>
            </ul>
        </div>
    </div>
  </nav>
</header>

