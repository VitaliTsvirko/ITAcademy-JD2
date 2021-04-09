<%@ page import="java.util.Locale" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="static/bootstrap-5.0.0/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/navbar.css" rel="stylesheet">

<header>
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-xxl">
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}">Аэропорты</a>
            </li>
            <li class="nav-item">
               <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/search">Поиск билетов</a>
            </li>
         </ul>
        </div>

        <ul class="navbar-nav" style="margin-right: 30px;">
            <li>
                <form action="lang" method="POST">
                    <c:choose>
                        <c:when test="${sessionScope.lang.equalsIgnoreCase(\"en\")}">
                            <input type="submit" class="btn btn-outline-secondary" name="lang" value="Ru">
                            <input type="submit" class="btn btn-outline-primary" name="lang" value="En">
                        </c:when>
                        <c:otherwise>
                            <input type="submit" class="btn btn-outline-primary" name="lang" value="Ru">
                            <input type="submit" class="btn btn-outline-secondary" name="lang" value="En">
                        </c:otherwise>
                    </c:choose>
                </form>
            </li>
        </ul>
    </div>
  </nav>
</header>