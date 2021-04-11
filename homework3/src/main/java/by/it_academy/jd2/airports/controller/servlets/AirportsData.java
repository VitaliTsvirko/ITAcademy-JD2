package by.it_academy.jd2.airports.controller.servlets;

import by.it_academy.jd2.airports.model.dto.Lang;
import by.it_academy.jd2.airports.service.AirportsDataService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Vitali Tsvirko
 */
public class AirportsData extends HttpServlet {
    private final AirportsDataService airportsDataService = AirportsDataService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lang lang = (Lang) req.getSession().getAttribute("lang");

        try{
            req.setAttribute("allAirportsData", airportsDataService.getAllAirportsData(lang));
        } catch (ClassNotFoundException | SQLException | PropertyVetoException e) {
            req.setAttribute("error", true);
            req.setAttribute("errorMessage", "Ошибка работы с базой данных. Попробуйте повторить запрос позже...");
        }

        req.getRequestDispatcher("airports.jsp").forward(req, resp);
    }
}
