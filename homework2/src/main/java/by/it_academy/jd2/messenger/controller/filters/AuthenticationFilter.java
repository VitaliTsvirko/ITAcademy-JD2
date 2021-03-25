package by.it_academy.jd2.messenger.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
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
