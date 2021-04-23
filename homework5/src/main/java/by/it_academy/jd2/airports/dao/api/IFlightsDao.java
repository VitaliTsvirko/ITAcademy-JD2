package by.it_academy.jd2.airports.dao.api;

import by.it_academy.jd2.airports.core.dto.FlightSearchParam;
import by.it_academy.jd2.airports.core.dto.Flights;

import java.util.List;

public interface IFlightsDao {
    List<Flights> findFlightsByAirportsOrDates(FlightSearchParam searchParam, int limit, int offset) throws IllegalAccessException;
    int getFlightsCountByAirportsCodeOrDates(FlightSearchParam searchParam) throws IllegalAccessException;
}
