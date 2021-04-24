package by.it_academy.jd2.airports.dao.hibernate;

import by.it_academy.jd2.airports.core.dto.FlightSearchParam;
import by.it_academy.jd2.airports.core.dto.Flights;
import by.it_academy.jd2.airports.core.dto.Lang;
import by.it_academy.jd2.airports.dao.api.IFlightsDao;
import by.it_academy.jd2.airports.dao.nativesql.FlightsDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightsHibernateDaoTest {
    private final IFlightsDao nativeSqlDao = new FlightsDao();
    private final IFlightsDao hibernateDao = new FlightsHibernateDao();

    FlightsHibernateDaoTest() throws PropertyVetoException {
    }


    @Test
    void getFlightsCountByAirportsCodeOrDates_SearchParamWithoutDate_CountEquals() throws IllegalAccessException {
        FlightSearchParam searchParam = new FlightSearchParam();
        searchParam.setDepartureAirport("DME");
        searchParam.setArrivalAirport("DME");

        int count1 = nativeSqlDao.getFlightsCountByAirportsCodeOrDates(searchParam);
        int count2 = hibernateDao.getFlightsCountByAirportsCodeOrDates(searchParam);

        Assertions.assertEquals(count1, count2);
    }

    @Test
    void getFlightsCountByAirportsCodeOrDates_SearchFullParam_CountEquals() throws IllegalAccessException {
        FlightSearchParam searchParam = new FlightSearchParam();
        searchParam.setDepartureAirport("DME");
        searchParam.setArrivalAirport("DME");
        searchParam.setDepartureDate("2017-04-09");
        searchParam.setArrivalDate("2017-04-09");

        int count1 = nativeSqlDao.getFlightsCountByAirportsCodeOrDates(searchParam);
        int count2 = hibernateDao.getFlightsCountByAirportsCodeOrDates(searchParam);

        Assertions.assertEquals(count1, count2);
    }

    @Test
    void findFlightsByAirportsOrDates_SearchParamWithoutDate_ListEqual() throws IllegalAccessException {
        FlightSearchParam searchParam = new FlightSearchParam();
        searchParam.setDepartureAirport("DME");
        searchParam.setArrivalAirport("ROV");
        searchParam.setLang(Lang.RU);

        List<Flights> flight1 = nativeSqlDao.findFlightsByAirportsOrDates(searchParam, 0, 0);
        List<Flights> flight2 = hibernateDao.findFlightsByAirportsOrDates(searchParam, 0, 0);

        assertEquals(flight1, flight2);
    }

    @Test
    void findFlightsByAirportsOrDates_SearchFullParam_ListEqual() throws IllegalAccessException {
        FlightSearchParam searchParam = new FlightSearchParam();
        searchParam.setDepartureAirport("DME");
        searchParam.setArrivalAirport("ROV");
        searchParam.setDepartureDate("2017-04-09");
        searchParam.setArrivalDate("2017-04-09");
        searchParam.setLang(Lang.RU);

        List<Flights> flight1 = nativeSqlDao.findFlightsByAirportsOrDates(searchParam, 0, 0);
        List<Flights> flight2 = hibernateDao.findFlightsByAirportsOrDates(searchParam, 0, 0);

        assertEquals(flight1, flight2);
    }
}