<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Ошибка</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

</head>

<body>
    <%@include file="header.jsp"%>

    <main class="container">
        <div class="starter-template text-center py-5 px-3">
            <c:choose>
                <c:when test="${empty requestScope.storageError}">
                    <p style="font-size: 32px">Упс!!!!</p>
                    <p style="font-size: 20px">Ошибка 404 - Запрашиваемая страница не найдена</p>
                    <p class="login-callout mt-3"> Перейти <a href="${pageContext.request.contextPath}/chat">на главную страницу</a> </p>
                </c:when>
                <c:otherwise>
                    <p style="font-size: 32px">Упс!!!!</p>
                    <p style="font-size: 20px">Произошла ошибка работы с базой...</p>
                    <p class="login-callout mt-3"> Перейти <a href="${pageContext.request.contextPath}/chat">на главную страницу</a> </p>
                </c:otherwise>
            </c:choose>
        </div>
    </main>

    <%@include file="footer.jsp"%>
</body>
</html>
