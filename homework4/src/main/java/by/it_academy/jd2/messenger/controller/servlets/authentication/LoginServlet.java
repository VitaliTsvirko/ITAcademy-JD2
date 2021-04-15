package by.it_academy.jd2.messenger.controller.servlets.authentication;

import by.it_academy.jd2.messenger.model.storage.api.IUsers;
import by.it_academy.jd2.messenger.model.dto.User;
import by.it_academy.jd2.messenger.service.api.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Данный класс предназначен обработки авторизации пользователя мессенджера
 * <p>Данные пользователя передаются при помощи POST, параметры: </p>
 * <p>name - имя пользователя</p>
 * <p>password - пароль</p>
 *
 * <p>Если данные авторизации верные то происходит редирект на страницу чата,
 * и имя пользователя сохраняется в HTTP сесии,
 * в противном случае выводиться сообщение о неверных данных</p>
 *
 * @author Vitali Tsvirko
 *
 * @see User
 * @see IUsers
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserService userService = (IUserService) getServletContext().getAttribute("userService");

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        try{
            User user = userService.authentication(name, password);

            if (user != null){
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(Math.toIntExact(TimeUnit.MINUTES.toSeconds(15)));
                resp.sendRedirect(req.getContextPath() + "/chat");
            } else {
                req.setAttribute("loginResult", "Введено неверное имя пользователя или пароль!");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (IllegalAccessException e){
            req.setAttribute("storageError", "Произошла ошибка работы с базой...");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }

    }
}
