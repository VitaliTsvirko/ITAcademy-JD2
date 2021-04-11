package by.it_academy.jd2.airports.controller.servlets;

import by.it_academy.jd2.airports.model.dto.Flights;
import by.it_academy.jd2.airports.model.dto.Lang;
import by.it_academy.jd2.airports.service.AirportsDataService;
import by.it_academy.jd2.airports.service.FlightsSearcherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Vitali Tsvirko
 */
@WebServlet(urlPatterns = "/search")
public class FlightsSearch extends HttpServlet {
    private final FlightsSearcherService flightsSearcherService = FlightsSearcherService.getInstance();
    private final AirportsDataService airportsDataService = AirportsDataService.getInstance();
    private Map<String, String> airportsMap;
    private final int ITEMS_PER_PAGE = 25;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lang lang = (Lang) req.getSession().getAttribute("lang");

        try{
            this.airportsMap = airportsDataService.getAllAirportsCodeAndName(lang);
            req.setAttribute("airportsMap", airportsMap);

            //some filter select
            if (req.getQueryString() != null){
                filerHandler(req, lang);
            }
        } catch (IllegalArgumentException e){
            req.setAttribute("error", true);
            req.setAttribute("errorMessage", e.getMessage());
        } catch (ClassNotFoundException | SQLException | PropertyVetoException e) {
            req.setAttribute("error", true);
            req.setAttribute("errorMessage", "Ошибка работы с базой данных. Попробуйте повторить запрос позже...");
        }

        req.getRequestDispatcher("flightsSearch.jsp").forward(req, resp);
    }


    private void filerHandler(HttpServletRequest req, Lang lang) throws SQLException {
        String departureDate = req.getParameter("departureDate");
        String departureAirport = req.getParameter("departureAirport");
        String arrivalDate = req.getParameter("arrivalDate");
        String arrivalAirport = req.getParameter("arrivalAirport");
        String pageNo = req.getParameter("pageNo");

        int flightsTotalCount = flightsSearcherService.getFlightsCount(departureAirport, arrivalAirport, departureDate, arrivalDate);

        if(flightsTotalCount > 0){
            int totalPages = (int) Math.ceil(flightsTotalCount * 1.0 / ITEMS_PER_PAGE);
            int requestPageNo = (pageNo != null) ? Math.min(Integer.parseInt(pageNo), totalPages) : 1;

            List<Flights> flights = flightsSearcherService.findFlights(lang, departureAirport, arrivalAirport, departureDate, arrivalDate, ITEMS_PER_PAGE, requestPageNo);
            req.setAttribute("flightsData", flights);

            req.setAttribute("totalPages", totalPages);
            req.setAttribute("pageNo", requestPageNo);
        }

        req.setAttribute("departureDate", departureDate);
        req.setAttribute("departureAirport", departureAirport);
        req.setAttribute("arrivalDate", arrivalDate);
        req.setAttribute("arrivalAirport", arrivalAirport);
        req.setAttribute("flightsTotalCount", flightsTotalCount);
    }

}
