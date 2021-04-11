package by.it_academy.jd2.airports.service.api;

import by.it_academy.jd2.airports.model.dto.FlightSearchParam;
import by.it_academy.jd2.airports.model.dto.Flights;
import by.it_academy.jd2.airports.model.dto.Lang;

import java.sql.SQLException;
import java.util.List;

public interface IFlightsSearcherService {
    List<Flights> findFlights(Lang lang, FlightSearchParam searchParam) throws SQLException, IllegalArgumentException;
    int getFlightsCount (FlightSearchParam searchParam) throws SQLException;
}
