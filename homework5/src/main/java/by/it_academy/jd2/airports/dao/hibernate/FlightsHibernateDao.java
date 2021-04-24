package by.it_academy.jd2.airports.dao.hibernate;

import by.it_academy.jd2.airports.core.dto.FlightSearchParam;
import by.it_academy.jd2.airports.core.dto.Flights;
import by.it_academy.jd2.airports.core.utils.StringUtils;
import by.it_academy.jd2.airports.dao.api.IFlightsDao;
import by.it_academy.jd2.airports.dao.hibernate.core.HibernateCreator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

/**
 * @author Vitali Tsvirko
 */
public class FlightsHibernateDao implements IFlightsDao {
    private final Session dataSource = HibernateCreator.getInstance().openSession();
    private final ZoneOffset offset = ZoneOffset.UTC;

    @Override
    public List<Flights> findFlightsByAirportsOrDates(FlightSearchParam searchParam, int limit, int offset) throws IllegalAccessException {
        Query query;

        try{
            if (StringUtils.isAnyNullOrEmpty(searchParam.getDepartureDate(), searchParam.getArrivalDate())){
                query = dataSource.createQuery("from Flights WHERE departureAirportCode=:departureAirportCode AND arrivalAirportCode=:arrivalAirportCode");

                query.setParameter("departureAirportCode", searchParam.getDepartureAirport());
                query.setParameter("arrivalAirportCode", searchParam.getArrivalAirport());
                query.setFirstResult(offset);
                query.setMaxResults(limit);
            } else {
                query = dataSource.createQuery("from Flights WHERE (departureAirportCode=:departureAirportCode AND scheduledDeparture>:scheduledDepartureBegin AND scheduledDeparture<=:scheduledDepartureEnd)" +
                        "OR (arrivalAirportCode=:arrivalAirportCode AND scheduledArrival>:scheduledArrivalBegin AND scheduledArrival<=:scheduledArrivalEnd)");
                query.setParameter("departureAirportCode", searchParam.getDepartureAirport());
                query.setParameter("arrivalAirportCode", searchParam.getArrivalAirport());
                query.setParameter("scheduledDepartureBegin", LocalDate.parse(searchParam.getDepartureDate()).atTime(00,00,00).atOffset(this.offset));
                query.setParameter("scheduledDepartureEnd", LocalDate.parse(searchParam.getDepartureDate()).atTime(23,59,59).atOffset(this.offset));
                query.setParameter("scheduledArrivalBegin", LocalDate.parse(searchParam.getArrivalDate()).atTime(00,00,00).atOffset(this.offset));
                query.setParameter("scheduledArrivalEnd", LocalDate.parse(searchParam.getArrivalDate()).atTime(23,59,59).atOffset(this.offset));
                query.setFirstResult(offset);
                query.setMaxResults(limit);
            }

            return (List<Flights>) query.list();
        }catch (HibernateException e) {
            throw new IllegalAccessException("Ошибка работы с базой данных");
        }
    }


    @Override
    public int getFlightsCountByAirportsCodeOrDates(FlightSearchParam searchParam) throws IllegalAccessException {
        Query query;

        try {
            if (StringUtils.isAnyNullOrEmpty(searchParam.getDepartureDate(), searchParam.getArrivalDate())){
                query = dataSource.createQuery("Select count (*) from Flights WHERE departureAirportCode=:departureAirportCode AND arrivalAirportCode=:arrivalAirportCode");
                query.setParameter("departureAirportCode", searchParam.getDepartureAirport());
                query.setParameter("arrivalAirportCode", searchParam.getArrivalAirport());

            } else {
                query = dataSource.createQuery("Select count (*) from Flights WHERE (departureAirportCode=:departureAirportCode AND scheduledDeparture>:scheduledDepartureBegin AND scheduledDeparture<=:scheduledDepartureEnd)" +
                        "OR (arrivalAirportCode=:arrivalAirportCode AND scheduledArrival>:scheduledArrivalBegin AND scheduledArrival<=:scheduledArrivalEnd)");
                query.setParameter("departureAirportCode", searchParam.getDepartureAirport());
                query.setParameter("arrivalAirportCode", searchParam.getArrivalAirport());
                query.setParameter("scheduledDepartureBegin", LocalDate.parse(searchParam.getDepartureDate()).atTime(00,00,00).atOffset(this.offset));
                query.setParameter("scheduledDepartureEnd", LocalDate.parse(searchParam.getDepartureDate()).atTime(23,59,59).atOffset(this.offset));
                query.setParameter("scheduledArrivalBegin", LocalDate.parse(searchParam.getArrivalDate()).atTime(00,00,00).atOffset(this.offset));
                query.setParameter("scheduledArrivalEnd", LocalDate.parse(searchParam.getArrivalDate()).atTime(23,59,59).atOffset(this.offset));
            }

            Long result = (Long) query.getSingleResult();

            return result.intValue();
        } catch (HibernateException e){
            throw new IllegalAccessException("Ошибка работы с базой данных");
        }
    }

}
