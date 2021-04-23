package by.it_academy.jd2.airports.service.api;

import by.it_academy.jd2.airports.core.dto.FlightSearchParam;
import by.it_academy.jd2.airports.core.dto.Flights;
import by.it_academy.jd2.airports.core.dto.FlightsPageParam;
import by.it_academy.jd2.airports.core.dto.Lang;

import java.sql.SQLException;
import java.util.List;

public interface IFlightsSearcherService {
    List<Flights> findFlights(FlightSearchParam searchParam, FlightsPageParam pageParam) throws IllegalArgumentException, IllegalAccessException;
    int getFlightsCount (FlightSearchParam searchParam) throws IllegalAccessException;
}
