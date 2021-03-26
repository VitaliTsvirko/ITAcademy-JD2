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
 * @author Vitali Tsvirko
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
