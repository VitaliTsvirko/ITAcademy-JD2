package by.it_academy.jd2.airports.controller.servlets;

import by.it_academy.jd2.airports.core.dto.FlightSearchParam;
import by.it_academy.jd2.airports.core.dto.Flights;
import by.it_academy.jd2.airports.core.dto.FlightsPageParam;
import by.it_academy.jd2.airports.core.dto.Lang;
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

    public FlightsSearch() throws IllegalAccessException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Lang lang = (Lang) req.getSession().getAttribute("lang");
        FlightSearchParam searchParam = new FlightSearchParam();

        searchParam.setDepartureDate(req.getParameter("departureDate"));
        searchParam.setDepartureAirport(req.getParameter("departureAirport"));
        searchParam.setArrivalDate(req.getParameter("arrivalDate"));
        searchParam.setArrivalAirport(req.getParameter("arrivalAirport"));
        searchParam.setQueryPageNo(req.getParameter("pageNo"));
        searchParam.setLang(lang);

        try{
            this.airportsMap = airportsDataService.getAllAirportsCodeAndName(lang);
            req.setAttribute("airportsMap", airportsMap);

            //some filter select
            if (req.getQueryString() != null){
                FlightsPageParam pageParam = new FlightsPageParam();
                pageParam.setPageItemLimit(25);

                List<Flights> flights = flightsSearcherService.findFlights(searchParam, pageParam);

                req.setAttribute("pageParam", pageParam);
                req.setAttribute("flightsData", flights);

                String queryString = req.getQueryString().replace("&pageNo="+pageParam.getPageNo(), "");
                req.setAttribute("queryString", queryString);
            }
        } catch (IllegalArgumentException e){
            req.setAttribute("error", true);
            req.setAttribute("errorMessage", e.getMessage());
        } catch (IllegalAccessException e) {
            req.setAttribute("error", true);
            req.setAttribute("errorMessage", "Ошибка работы с базой данных. Попробуйте повторить запрос позже...");
        }

        req.setAttribute("searchParam", searchParam);
        req.getRequestDispatcher("flightsSearch.jsp").forward(req, resp);
    }
}
