package by.it_academy.jd2.messenger.controller.servlets;

import by.it_academy.jd2.messenger.model.storage.api.IMessages;
import by.it_academy.jd2.messenger.model.storage.api.IUsers;
import by.it_academy.jd2.messenger.model.dto.Message;
import by.it_academy.jd2.messenger.model.dto.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Данный класс предназначен работы с сообщениями чата
 * <p>Данные сообщения передаются при помощи POST, параметры: </p>
 * <p>message - текст сообщения</p>
 *
 * <p>Данные о пользователя берутся из HTTP сессии</p>
 * <p>После получения сообщения и данных пользователя информация передается в {@code messageContainer}</p>
 * <p>Затем происходит переадресация на страницу чата, а данные хранилища сообщений передаются в атрибутах запроса</p>
 *
 * @author Vitali Tsvirko
 *
 * @see Message
 * @see IMessages
 */
public class ChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IMessages messageContainer = (IMessages) getServletContext().getAttribute("messageContainer");

        req.setAttribute("messages", messageContainer);
        req.getRequestDispatcher("chat.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUsers usersContainer = (IUsers) getServletContext().getAttribute("usersContainer");
        IMessages messageContainer = (IMessages) getServletContext().getAttribute("messageContainer");

        User user = usersContainer.getByName(req.getSession().getAttribute("user").toString());

        String message = req.getParameter("message");

        messageContainer.add(new Message(user.getName(), message, LocalDateTime.now()));

        req.setAttribute("messages", messageContainer);
        req.getRequestDispatcher("chat.jsp").forward(req, resp);
    }
}
