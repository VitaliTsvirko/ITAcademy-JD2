<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Чат</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <link href="static/css/chat.css" rel="stylesheet">
</head>

<body>
    <%@include file="header.jsp"%>

    <main class="container-xxl">

        <div class="row">
            <div class="col msg-window-container">
                <div class="card" id="msgWindow">
                    <div class="card-header"><span class="card-title">Общий чат</span></div>

                    <div class="msg-windows">
                        <c:forEach items="${requestScope.messages}"
                                   var="message">
                            <p class="msg-p">
                                <fmt:parseDate value="${message.messageTimeStamp}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedTimeStamp" type="both" />
                                <span class="msg-time"> <fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${parsedTimeStamp}"/> </span>
                                <span class="msg-user"> ${message.user}: </span>
                                <span class="msg-message"> <c:out value="${message.message}"/></span>
                            </p>
                        </c:forEach>
                    </div>

                    <div class="card-footer">
                            <form action="chat" method="post" class="input-group">
                                <input name="message" class="form-control" type="text" placeholder="Сообщение" required autofocus/>
                                <div class="input-group-append">
                                    <button class="btn btn btn-success" type="submit">Отправить</button>
                                </div>
                            </form>
                    </div>
                </div>
            </div>
        </div>

    </main>

    <%@include file="footer.jsp"%>
</body>
</html>
