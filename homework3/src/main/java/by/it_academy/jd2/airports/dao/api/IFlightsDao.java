package by.it_academy.jd2.airports.dao.api;

import by.it_academy.jd2.airports.core.dto.Flights;
import by.it_academy.jd2.airports.core.dto.Lang;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IFlightsDao {
    int getFlightsCountByAirportsCode(String departureAirportCode, String arrivalAirportCode) throws IllegalAccessException;
    int getFlightsCountByAirportsCodeAndDates(String departureAirportCode, String arrivalAirportCode, LocalDate departureDate, LocalDate arrivalDate) throws IllegalAccessException;
    List<Flights> getFlightsByAirportsCodeAndDates(Lang lang, String departureAirportCode, String arrivalAirportCode, LocalDate departureDate, LocalDate arrivalDate, int limit, int offset) throws IllegalAccessException;
    List<Flights> getFlightsByAirportsCode(Lang lang, String departureAirportCode, String arrivalAirportCode, int limit, int offset) throws IllegalAccessException;
}
