package by.it_academy.jd2.airports.model.dao.api;

import by.it_academy.jd2.airports.model.dto.Flights;
import by.it_academy.jd2.airports.model.dto.Lang;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IFlightsDao {
    int getFlightsCountByAirportsCode(String departureAirportCode, String arrivalAirportCode) throws SQLException;
    int getFlightsCountByAirportsCodeAndDates(String departureAirportCode, String arrivalAirportCode, LocalDate departureDate, LocalDate arrivalDate) throws SQLException;
    List<Flights> getFlightsByAirportsCodeAndDates(Lang lang, String departureAirportCode, String arrivalAirportCode, LocalDate departureDate, LocalDate arrivalDate, int limit, int offset) throws SQLException;
    List<Flights> getFlightsByAirportsCode(Lang lang, String departureAirportCode, String arrivalAirportCode, int limit, int offset) throws SQLException;
}
