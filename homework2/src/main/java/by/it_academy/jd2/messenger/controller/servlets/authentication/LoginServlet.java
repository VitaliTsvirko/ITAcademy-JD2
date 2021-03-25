package by.it_academy.jd2.messenger.controller.servlets.authentication;

import by.it_academy.jd2.messenger.model.storage.api.IUsers;
import by.it_academy.jd2.messenger.model.dto.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Vitali Tsvirko
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUsers usersContainer = (IUsers) getServletContext().getAttribute("usersContainer");

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = usersContainer.getByName(name);

        if (user != null && user.getPassword().equals(password)){
            HttpSession session = req.getSession();
            session.setAttribute("user", user.getName());
            session.setMaxInactiveInterval(Math.toIntExact(TimeUnit.MINUTES.toSeconds(15)));

            resp.sendRedirect(req.getContextPath() + "/chat");
        } else {
            req.setAttribute("login-result", "Введено неверное имя пользователя или пароль!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
