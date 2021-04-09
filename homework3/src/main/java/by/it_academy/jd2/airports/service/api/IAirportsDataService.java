package by.it_academy.jd2.airports.service.api;

import by.it_academy.jd2.airports.model.dto.Tickets;
import by.it_academy.jd2.airports.model.dto.AirportsData;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface IAirportsDataService {
    List<AirportsData> getAllAirportsData (String langCode) throws ClassNotFoundException, SQLException, PropertyVetoException;
    Map<String, String> getAllAirportsList (String langCode) throws ClassNotFoundException, SQLException, PropertyVetoException;
    List<Tickets> findTickets (String departureAirportCode, String arrivalAirportCode, String departureDate, String arrivalDate, int limit, int offset);
}
