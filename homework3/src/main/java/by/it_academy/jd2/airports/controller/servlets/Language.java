package by.it_academy.jd2.airports.controller.servlets;

import by.it_academy.jd2.airports.model.dto.Lang;
import com.oracle.wls.shaded.org.apache.bcel.generic.LNEG;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Created by Vitali Tsvirko
 */
@WebServlet(urlPatterns = "/lang")
public class Language extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");

        if (lang != null){
            if (lang.equalsIgnoreCase(Lang.EN.getTextCode())){
                req.getSession().setAttribute("lang", Lang.EN);
            } else {
                req.getSession().setAttribute("lang",  Lang.RU);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/airports");
    }
}
