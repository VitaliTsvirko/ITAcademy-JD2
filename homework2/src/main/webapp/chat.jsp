<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="by.it_academy.jd2.messenger.model.dto.Message" %>
<%@ page import="by.it_academy.jd2.messenger.model.storage.api.IMessages" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
                        <%
                            IMessages messages = (IMessages) request.getAttribute("messages");
                            for (Message message : messages.getAll()) {
                        %>
                        <p class="msg-p">
                            <span class="msg-time"> <%= message.getMessageTimeStamp().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")) %> </span>
                            <span class="msg-user"> <%= message.getUser() %>: </span>
                            <span class="msg-message"> <%= message.getMessage() %></span>
                        </p>
                        <%
                        }
                        %>
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
