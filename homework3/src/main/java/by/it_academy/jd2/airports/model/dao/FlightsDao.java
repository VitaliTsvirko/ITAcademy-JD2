package by.it_academy.jd2.airports.model.dao;

import by.it_academy.jd2.airports.model.dao.core.ConnectionPoolCreator;
import by.it_academy.jd2.airports.model.dto.Flights;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitali Tsvirko
 */
public class FlightsDao {
    private final DataSource dataSource = ConnectionPoolCreator.getInstance();

    public FlightsDao() throws PropertyVetoException {
    }

    public long getTicketsCountByAirportsCode(String departureAirportCode, String arrivalAirportCode){
        long result = 0;
        String sql = "SELECT count(*) FROM flights WHERE departure_airport=? AND arrival_airport=?";

        try (Connection connection = dataSource.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, departureAirportCode);
                ps.setString(2, arrivalAirportCode);

                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    result = rs.getLong("count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }



    public List<Flights> getFlightsByAirportsCodeAndDate(String departureAirportCode, String arrivalAirportCode, LocalDate departureDate){
        List<Flights> result = new ArrayList<>();

        return result;
    }


    public List<Flights> getFlightsByAirportsCode(String departureAirportCode, String arrivalAirportCode, int limit, int offset){
        List<Flights> result = new ArrayList<>();
        String sql = "SELECT flight_no, scheduled_departure, scheduled_arrival, departure_airport,  arrival_airport " +
                     "FROM flights WHERE departure_airport=? AND arrival_airport=? LIMIT ? OFFSET ?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, departureAirportCode);
                ps.setString(2, arrivalAirportCode);
                ps.setInt(3, limit);
                ps.setInt(4, offset);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Flights ticket = new Flights();
                        ticket.setFlight_no(rs.getString("flight_no"));
                        ticket.setScheduled_departure(rs.getString("scheduled_departure"));
                        ticket.setScheduled_arrival(rs.getString("scheduled_arrival"));
                        ticket.setDeparture_airport(rs.getString("departure_airport"));
                        ticket.setArrival_airport(rs.getString("arrival_airport"));
                        result.add(ticket);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


}
