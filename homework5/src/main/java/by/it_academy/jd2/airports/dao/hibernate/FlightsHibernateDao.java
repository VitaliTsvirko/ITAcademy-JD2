package by.it_academy.jd2.airports.dao.hibernate;

import by.it_academy.jd2.airports.core.dto.Flights;
import by.it_academy.jd2.airports.core.dto.Lang;
import by.it_academy.jd2.airports.dao.api.IFlightsDao;
import by.it_academy.jd2.airports.dao.core.HibernateCreator;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Vitali Tsvirko
 */
public class FlightsHibernateDao implements IFlightsDao {
    private final Session dataSource = HibernateCreator.getInstance().openSession();
    private final ZoneOffset offset = ZoneOffset.UTC;

    public FlightsHibernateDao() throws PropertyVetoException {
    }

    @Override
    public int getFlightsCountByAirportsCode(String departureAirportCode, String arrivalAirportCode) throws IllegalAccessException {


        Query query = dataSource.createQuery("Select count (*) from Flights WHERE departureAirportCode=:departureAirportCode AND arrivalAirportCode=:arrivalAirportCode");
        query.setParameter("departureAirportCode", departureAirportCode);
        query.setParameter("arrivalAirportCode", arrivalAirportCode);

        Long result = (Long) query.getSingleResult();

        return result.intValue();
    }

    @Override
    public int getFlightsCountByAirportsCodeAndDates(String departureAirportCode, String arrivalAirportCode, LocalDate departureDate, LocalDate arrivalDate) throws IllegalAccessException {
        Query query = dataSource.createQuery("Select count (*) from Flights WHERE (departureAirportCode=:departureAirportCode AND scheduledDeparture>:scheduledDepartureBegin AND scheduledDeparture<=:scheduledDepartureEnd)" +
                "OR (arrivalAirportCode=:arrivalAirportCode AND scheduledArrival>:scheduledArrivalBegin AND scheduledArrival<=:scheduledArrivalEnd)");
        query.setParameter("departureAirportCode", departureAirportCode);
        query.setParameter("arrivalAirportCode", arrivalAirportCode);
        query.setParameter("scheduledDepartureBegin", departureDate.atTime(00,00,00).atOffset(this.offset));
        query.setParameter("scheduledDepartureEnd", departureDate.atTime(23,59,59).atOffset(this.offset));
        query.setParameter("scheduledArrivalBegin", arrivalDate.atTime(00,00,00).atOffset(this.offset));
        query.setParameter("scheduledArrivalEnd", arrivalDate.atTime(23,59,59).atOffset(this.offset));

        Long result = (Long) query.getSingleResult();

        return result.intValue();
    }

    @Override
    public List<Flights> getFlightsByAirportsCodeAndDates(Lang lang, String departureAirportCode, String arrivalAirportCode, LocalDate departureDate, LocalDate arrivalDate, int limit, int offset) throws IllegalAccessException {
        Query query = dataSource.createQuery("from Flights WHERE (departureAirportCode=:departureAirportCode AND scheduledDeparture>:scheduledDepartureBegin AND scheduledDeparture<=:scheduledDepartureEnd)" +
                "OR (arrivalAirportCode=:arrivalAirportCode AND scheduledArrival>:scheduledArrivalBegin AND scheduledArrival<=:scheduledArrivalEnd)");
        query.setParameter("departureAirportCode", departureAirportCode);
        query.setParameter("arrivalAirportCode", arrivalAirportCode);
        query.setParameter("scheduledDepartureBegin", departureDate.atTime(00,00,00).atOffset(this.offset));
        query.setParameter("scheduledDepartureEnd", departureDate.atTime(23,59,59).atOffset(this.offset));
        query.setParameter("scheduledArrivalBegin", arrivalDate.atTime(00,00,00).atOffset(this.offset));
        query.setParameter("scheduledArrivalEnd", arrivalDate.atTime(23,59,59).atOffset(this.offset));
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        List<Flights> result = (List<Flights>) query.list();

        return result;
    }

    @Override
    public List<Flights> getFlightsByAirportsCode(Lang lang, String departureAirportCode, String arrivalAirportCode, int limit, int offset) throws IllegalAccessException {

        Query query = dataSource.createQuery("from Flights WHERE departureAirportCode=:departureAirportCode AND arrivalAirportCode=:arrivalAirportCode");
        query.setParameter("departureAirportCode", departureAirportCode);
        query.setParameter("arrivalAirportCode", arrivalAirportCode);
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        List<Flights> result = (List<Flights>) query.list();

        return result;
    }


    public static void main(String[] args) throws PropertyVetoException, IllegalAccessException {
        FlightsHibernateDao dao = new FlightsHibernateDao();

        List<Flights> flightsByAirportsCode = dao.getFlightsByAirportsCode(Lang.RU, "DME", "ROV", 25, 50);


        List<Flights> flightsByAirportsCodeAndDates = dao.getFlightsByAirportsCodeAndDates(Lang.RU, "DME", "ROV", LocalDate.parse("2017-04-09"), LocalDate.parse("2017-04-09"), 25, 50);

        System.out.println(flightsByAirportsCode.size());

        LocalDateTime now = LocalDateTime.now();

        System.out.println(now);

        LocalDate date = LocalDate.now();
        ZoneOffset offset = ZoneOffset.UTC;
        OffsetDateTime odt = date.atStartOfDay(offset).toOffsetDateTime();

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");
        String format1 = flightsByAirportsCodeAndDates.get(1).getActualDeparture().toLocalDateTime().format(dateTimeFormat);


        LocalDateTime parse = date.atTime(23,59,00);;
        odt = parse.atOffset(offset);

    }


}
