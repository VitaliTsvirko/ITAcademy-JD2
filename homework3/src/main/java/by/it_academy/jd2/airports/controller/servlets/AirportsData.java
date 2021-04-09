package by.it_academy.jd2.airports.controller.servlets;

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
        if (req.getSession().getAttribute("lang") == null){
            req.getSession().setAttribute("lang", "ru");
        }

        try{
            req.setAttribute("allAirportsData", airportsDataService.getAllAirportsData(req.getSession().getAttribute("lang").toString()));
        } catch (ClassNotFoundException | SQLException | PropertyVetoException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("airports.jsp").forward(req, resp);
    }
}
