package by.it_academy.jd2.messenger.controller.servlets.authentication;

import by.it_academy.jd2.messenger.model.dto.User;
import by.it_academy.jd2.messenger.model.storage.api.IUsers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Данный класс предназначен обработки выхода пользователя из мессенджера
 *
 * <p>При выходе данные пользователя удаляются из HTTP сессии</p>
 *
 * @author Vitali Tsvirko
 *
 */
@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("user", null);

        resp.sendRedirect(req.getContextPath()+"/chat");
    }
}
