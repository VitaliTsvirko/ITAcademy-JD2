package by.it_academy.jd2.airports.controller.servlets;

import by.it_academy.jd2.airports.model.dto.FlightSearchParam;
import by.it_academy.jd2.airports.model.dto.Flights;
import by.it_academy.jd2.airports.model.dto.Lang;
import by.it_academy.jd2.airports.service.AirportsDataService;
import by.it_academy.jd2.airports.service.FlightsSearcherService;
import by.it_academy.jd2.airports.service.api.IAirportsDataService;
import by.it_academy.jd2.airports.service.api.IFlightsSearcherService;
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
    private final IFlightsSearcherService flightsSearcherService = FlightsSearcherService.getInstance();
    private final IAirportsDataService airportsDataService = AirportsDataService.getInstance();
    private Map<String, String> airportsMap;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lang lang = (Lang) req.getSession().getAttribute("lang");

        try{
            this.airportsMap = airportsDataService.getAllAirportsCodeAndName(lang);
            req.setAttribute("airportsMap", airportsMap);

            //some filter select
            if (req.getQueryString() != null){
                filterHandler(req, lang);
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


    private void filterHandler(HttpServletRequest req, Lang lang) throws SQLException {
        FlightSearchParam searchParam = new FlightSearchParam();

        searchParam.setDepartureDate(req.getParameter("departureDate"));
        searchParam.setDepartureAirport(req.getParameter("departureAirport"));
        searchParam.setArrivalDate(req.getParameter("arrivalDate"));
        searchParam.setArrivalAirport(req.getParameter("arrivalAirport"));
        searchParam.setPageNo(req.getParameter("pageNo"));
        searchParam.setPageItemLimit(25);

        int flightsTotalCount = flightsSearcherService.getFlightsCount(searchParam);

        if(flightsTotalCount > 0){
            int totalPages = (int) Math.ceil(flightsTotalCount * 1.0 / searchParam.getPageItemLimit());

            if (searchParam.getPageNo() > totalPages){
                searchParam.setPageNo(totalPages);
            }

            List<Flights> flights = flightsSearcherService.findFlights(lang, searchParam);
            req.setAttribute("flightsData", flights);

            req.setAttribute("totalPages", totalPages);
        }

        req.setAttribute("searchParam", searchParam);
        req.setAttribute("flightsTotalCount", flightsTotalCount);
    }

}
