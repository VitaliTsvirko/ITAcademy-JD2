package by.it_academy.jd2.messenger.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Данный класс предназначен для обработки аутентификации пользователя мессенджера.
 * <p>Данне о авторизации пользователя хранятся в HTTP сессии в параметре {@code user}</p>
 * <p>Если данных в сессии нет или срок хранения истек происходит редирект на страницу авторизации</p>
 *
 *  @author Vitali Tsvirko
 */
public class AuthenticationFilter implements Filter{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        HttpSession session = httpServletRequest.getSession();

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        if (isLoggedIn){
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendRedirect("login.jsp");
        }
    }
}
