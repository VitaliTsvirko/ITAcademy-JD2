package by.it_academy.jd2.airports.controller.servlets;

import by.it_academy.jd2.airports.model.dto.Tickets;
import by.it_academy.jd2.airports.service.AirportsDataService;
import by.it_academy.jd2.airports.service.TicketsSearcherService;
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
public class TicketsSearch extends HttpServlet {
    private final TicketsSearcherService ticketsSearcherService = TicketsSearcherService.getInstance();
    private final AirportsDataService airportsDataService = AirportsDataService.getInstance();
    private Map<String, String> airportsMap;
    private final int ITEMS_PER_PAGE = 25;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("lang") == null){
            req.getSession().setAttribute("lang", "ru");
        }

        try{
            this.airportsMap = airportsDataService.getAllAirportsCodeAndName(req.getSession().getAttribute("lang").toString());
            req.setAttribute("airportsMap", airportsMap);
        } catch (ClassNotFoundException | SQLException | PropertyVetoException e) {
            e.printStackTrace();
        }

        //some filter select
        if (req.getQueryString() != null){
                String departureDate = req.getParameter("departureDate");
                String departureAirport = req.getParameter("departureAirport");
                String arrivalDate = req.getParameter("arrivalDate");
                String arrivalAirport = req.getParameter("arrivalAirport");
                String pageNo = req.getParameter("pageNo");

                long ticketsTotalCount = ticketsSearcherService.getTicketsCount(departureAirport, arrivalAirport);

                if(ticketsTotalCount > 0){
                    int currentPage = (pageNo != null) ? Integer.parseInt(pageNo) : 1;

                    List<Tickets> tickets = ticketsSearcherService.findTickets(departureAirport, arrivalAirport, null, null, ITEMS_PER_PAGE, currentPage * ITEMS_PER_PAGE);
                    req.setAttribute("ticketsData", tickets);

                    req.setAttribute("totalPages", ticketsTotalCount / ITEMS_PER_PAGE);
                    req.setAttribute("pageNo", pageNo);
                }

                req.setAttribute("departureDate", departureDate);
                req.setAttribute("departureAirport", departureAirport);
                req.setAttribute("arrivalDate", arrivalDate);
                req.setAttribute("arrivalAirport", arrivalAirport);
                req.setAttribute("ticketsTotalCount", ticketsTotalCount);
        }

        req.getRequestDispatcher("ticketsSearch.jsp").forward(req, resp);
    }

}
