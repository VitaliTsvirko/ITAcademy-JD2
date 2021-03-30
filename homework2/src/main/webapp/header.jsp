<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="by.it_academy.jd2.messenger.model.dto.User" %>

<%
    User user = (User) request.getSession().getAttribute("user");
%>

<link href="static/bootstrap-5.0.0/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/navbar.css" rel="stylesheet">

<header>
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-xxl">
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="<%=request.getContextPath() + "/chat"%>">Мессенджер</a>
            </li>
         </ul>
        </div>

        <ul class="navbar-nav" style="margin-right: 30px;">
          <%
            if (user == null)  {
          %>
              <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath() + "/login.jsp"%>">Войти</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath() + "/signup.jsp"%>">Регистрация</a>
              </li>
          <%
          } else {
          %>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-expanded="false"><%= user.getName() %></a>
                <ul class="dropdown-menu" aria-labelledby="dropdown01" >
                  <li><a class="dropdown-item" href="<%=request.getContextPath() + "/logout"%>">Выйти</a></li>
                  <li><a class="dropdown-item" href="<%=request.getContextPath() + "/userinfo.jsp"%>">Профиль</a></li>
                </ul>
              </li>
          <%
            }
          %>
        </ul>
    </div>
  </nav>

</header>