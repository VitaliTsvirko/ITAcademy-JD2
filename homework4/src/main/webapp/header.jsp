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
              <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/chat">Мессенджер</a>
            </li>
         </ul>
        </div>

        <ul class="navbar-nav" style="margin-right: 30px;">
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/login.jsp">Войти</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/signup.jsp">Регистрация</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-expanded="false">${sessionScope.user.name}</a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown01" >
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Выйти</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/userinfo.jsp">Профиль</a></li>
                        </ul>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
  </nav>

</header>