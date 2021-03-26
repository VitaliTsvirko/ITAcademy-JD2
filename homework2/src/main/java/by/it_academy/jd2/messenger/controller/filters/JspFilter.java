package by.it_academy.jd2.messenger.controller.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Данный класс предназначен для обработки прямых запросов на страницы JSP.
 * <p>При прямом обращении к определенной JSP странице (указывается в Web.xml) происходит
 * редирект на страницу ошибки 404</p>
 *  @author Vitali Tsvirko
 */
public class JspFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/error");
    }
}
