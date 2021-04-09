package by.it_academy.jd2.airports.service;

import by.it_academy.jd2.airports.core.utils.StringUtils;
import by.it_academy.jd2.airports.model.dao.TicketsDao;
import by.it_academy.jd2.airports.model.dto.Tickets;

import java.beans.PropertyVetoException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Vitali Tsvirko
 */
public class TicketsSearcherService {
    private static volatile TicketsSearcherService instance;
    private final TicketsDao ticketsDao;

    private TicketsSearcherService() throws PropertyVetoException {
        ticketsDao = new TicketsDao();
    }

    /**
     * Данный метод метод предназначен для создания и возвращения объекта
     * <p>Если объект еще не создан, то создаться новый объект {@code UserService} и возвращается</p>
     * <p>Если объект был создан ранее, то он и возвращается</p>
     * @return возвращает объект {@code UserService}
     */
    public static TicketsSearcherService getInstance() {
        if (instance == null) {
            synchronized (TicketsSearcherService.class){
                if (instance == null) {
                    try {
                        instance = new TicketsSearcherService();
                    } catch (PropertyVetoException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

    public List<Tickets> findTickets (String departureAirportCode, String arrivalAirportCode, String departureDate, String arrivalDate, int limit, int offset) {
        if (StringUtils.isAnyNullOrEmpty(departureDate, arrivalDate)){
            return ticketsDao.getTicketsByAirportsCode(departureAirportCode, arrivalAirportCode, limit, offset);
        } else {
            return ticketsDao.getTicketsByAirportsCodeAndDate(departureAirportCode, arrivalAirportCode, LocalDate.parse(departureDate));
        }
    }

    public long getTicketsCount (String departureAirportCode, String arrivalAirportCode){
        return ticketsDao.getTicketsCountByAirportsCode(departureAirportCode, arrivalAirportCode);
    }

}
