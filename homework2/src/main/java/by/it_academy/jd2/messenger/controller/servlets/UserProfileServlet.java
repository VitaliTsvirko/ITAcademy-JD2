package by.it_academy.jd2.messenger.controller.servlets;

import by.it_academy.jd2.messenger.model.storage.api.IUsers;
import by.it_academy.jd2.messenger.model.dto.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Данный класс предназначен вывода информации о текущем пользователе
 *
 * @author Vitali Tsvirko
 */
@WebServlet(urlPatterns = "/userinfo")
public class UserProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUsers usersContainer = (IUsers) getServletContext().getAttribute("usersContainer");
        User user = usersContainer.getByName(req.getSession().getAttribute("user").toString());

        req.setAttribute("user", user);
        req.getRequestDispatcher("userinfo.jsp").forward(req, resp);
    }
}
