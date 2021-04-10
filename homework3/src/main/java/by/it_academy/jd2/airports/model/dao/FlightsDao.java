package by.it_academy.jd2.airports.model.dao;

import by.it_academy.jd2.airports.model.dao.core.ConnectionPoolCreator;
import by.it_academy.jd2.airports.model.dto.Flights;
import by.it_academy.jd2.airports.model.dto.Lang;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitali Tsvirko
 */
public class FlightsDao {
    private final DataSource dataSource = ConnectionPoolCreator.getInstance();

    public FlightsDao() throws PropertyVetoException {
    }

    public int getTicketsCountByAirportsCode(String departureAirportCode, String arrivalAirportCode){
        int result = 0;
        String sql = "SELECT count(*) FROM flights WHERE departure_airport=? AND arrival_airport=?";

        try (Connection connection = dataSource.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, departureAirportCode);
                ps.setString(2, arrivalAirportCode);

                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    result = rs.getInt("count");
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


    public List<Flights> getFlightsByAirportsCode(Lang lang, String departureAirportCode, String arrivalAirportCode, int limit, int offset){
        List<Flights> result = new ArrayList<>();
        String sql = "SELECT f.flight_no," +
                " f.departure_airport," +
                " f.arrival_airport," +
                " f.scheduled_departure," +
                " f.scheduled_arrival," +
                " f.actual_departure," +
                " f.actual_arrival," +
                " f.aircraft_code," +
                " ad.model ->> ? AS aircraft_model" +
                " FROM flights f, aircrafts_data ad" +
                " WHERE f.aircraft_code = ad.aircraft_code AND " +
                "         departure_airport=? AND arrival_airport=? LIMIT ? OFFSET ?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, lang.getTextCode());
                ps.setString(2, departureAirportCode);
                ps.setString(3, arrivalAirportCode);
                ps.setInt(4, limit);
                ps.setInt(5, offset);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Flights flights = new Flights();
                        flights.setFlight_no(rs.getString("flight_no"));
                        flights.setScheduled_departure(rs.getObject("scheduled_departure", OffsetDateTime.class));
                        flights.setScheduled_arrival(rs.getObject("scheduled_arrival", OffsetDateTime.class));
                        flights.setActual_departure(rs.getObject("actual_departure", OffsetDateTime.class));
                        flights.setActual_arrival(rs.getObject("actual_arrival", OffsetDateTime.class));
                        flights.setAircraft_model(rs.getString("aircraft_model"));

                        result.add(flights);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    public static void main(String[] args) throws PropertyVetoException {
        FlightsDao dao = new FlightsDao();

        List<Flights> flightsByAirportsCode = dao.getFlightsByAirportsCode(Lang.RU, "DME", "ROV", 25, 0);

        System.out.println(flightsByAirportsCode.size());

        LocalDateTime now = LocalDateTime.now();

        System.out.println(now);


    }


}
