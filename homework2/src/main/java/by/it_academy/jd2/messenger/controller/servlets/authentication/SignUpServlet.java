package by.it_academy.jd2.messenger.controller.servlets.authentication;

import by.it_academy.jd2.messenger.model.storage.api.IUsers;
import by.it_academy.jd2.messenger.model.dto.User;
import by.it_academy.jd2.messenger.service.api.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Данный класс предназначен обработки регистрации пользователя мессенджера
 * <p>Данные пользователя передаются при помощи POST, параметры: </p>
 * <p>name - имя пользователя</p>
 * <p>password - пароль</p>
 * <p>firstname - Имя</p>
 * <p>lastname - Фамилия</p>
 * <p>dateOfBirth - Дата рождения</p>
 *
 * <p>Если полученные данные пусты или пользователя с таким именем уже существует, то
 * выводиться сообщение о неверных данных,
 * в противном случае происходит редирект на страницу авторизации</p>
 *
 * @author Vitali Tsvirko
 *
 * @see User
 * @see IUsers
 */
@WebServlet(urlPatterns = "/signup")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserService usersService = (IUserService) getServletContext().getAttribute("userService");

        User user = new User();
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));
        user.setStringDateOfBirth(req.getParameter("dateOfBirth"));

        try{
            usersService.signUp(user);
            req.getSession().setAttribute("newUserSignUp", user.getName());
            resp.sendRedirect("login.jsp");
        } catch (IllegalArgumentException e) {
            req.setAttribute("signupResult", e.getMessage());
            req.getRequestDispatcher("signup.jsp").forward(req, resp);
        }
    }

}
