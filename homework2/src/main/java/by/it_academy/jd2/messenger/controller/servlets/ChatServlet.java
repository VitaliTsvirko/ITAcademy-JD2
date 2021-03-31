package by.it_academy.jd2.messenger.controller.servlets;

import by.it_academy.jd2.messenger.model.storage.api.IMessages;
import by.it_academy.jd2.messenger.model.dto.Message;
import by.it_academy.jd2.messenger.model.dto.User;
import by.it_academy.jd2.messenger.service.MessageService;
import by.it_academy.jd2.messenger.service.api.IMessageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
    private final IMessageService messageService;

    public ChatServlet(){
        //this.messageService = (IMessageService) getServletContext().getAttribute("messageService");
        this.messageService = MessageService.getInstance();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("messages", this.messageService.getAll());
        req.getRequestDispatcher("chat.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        //check user exist

        Message message = new Message();
        message.setUser(user.getName());
        message.setMessage(req.getParameter("message"));

        messageService.add(message);

        req.setAttribute("messages", messageService.getAll());
        req.getRequestDispatcher("chat.jsp").forward(req, resp);
    }
}
