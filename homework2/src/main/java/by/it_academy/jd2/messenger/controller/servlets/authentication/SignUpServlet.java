package by.it_academy.jd2.messenger.controller.servlets.authentication;

import by.it_academy.jd2.messenger.model.storage.api.IUsers;
import by.it_academy.jd2.messenger.model.dto.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Vitali Tsvirko
 */
@WebServlet(urlPatterns = "/signup")
public class SignUpServlet extends HttpServlet {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final String DATE_INPUT_ERROR_MESSAGE = "Ошибка ввода даты. Проверьте данные и повторите ввод.";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUsers usersContainer = (IUsers) getServletContext().getAttribute("usersContainer");

        try{
            usersContainer.add(new User(req.getParameter("name"),
                                        req.getParameter("password"),
                                        req.getParameter("firstname"),
                                        req.getParameter("lastname"),
                                        LocalDate.parse(req.getParameter("dateOfBirth"), dateTimeFormatter)));
        } catch (IllegalArgumentException e) {
            req.setAttribute("signup-result", e.getMessage());
            req.getRequestDispatcher("signup.jsp").forward(req, resp);
            return;
        } catch (DateTimeParseException e){
            req.setAttribute("signup-result", DATE_INPUT_ERROR_MESSAGE);
            req.getRequestDispatcher("signup.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect("login.jsp");
    }

}
