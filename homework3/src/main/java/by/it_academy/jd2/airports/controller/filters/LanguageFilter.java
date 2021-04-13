package by.it_academy.jd2.airports.controller.filters;

import by.it_academy.jd2.airports.model.dto.Lang;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * @author Vitali Tsvirko
 */
public class LanguageFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Lang lang = (Lang) httpServletRequest.getSession().getAttribute("lang");

        if (lang == null){
           httpServletRequest.getSession().setAttribute("lang", Lang.RU);
        }

        chain.doFilter(request, response);
    }
}
