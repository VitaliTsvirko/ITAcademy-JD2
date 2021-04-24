package by.it_academy.jd2.airports.dao.nativesql;

import by.it_academy.jd2.airports.core.dto.FlightSearchParam;
import by.it_academy.jd2.airports.core.utils.StringUtils;
import by.it_academy.jd2.airports.dao.api.IFlightsDao;
import by.it_academy.jd2.airports.dao.nativesql.core.ConnectionPoolCreator;
import by.it_academy.jd2.airports.core.dto.Flights;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitali Tsvirko
 */
public class FlightsDao implements IFlightsDao {
    private final DataSource dataSource = ConnectionPoolCreator.getInstance();
    private final ZoneOffset offset = ZoneOffset.UTC;

    @Override
    public List<Flights> findFlightsByAirportsOrDates(FlightSearchParam searchParam, int limit, int offset) throws IllegalAccessException {
        if (StringUtils.isAnyNullOrEmpty(searchParam.getDepartureDate(), searchParam.getArrivalDate())) {
            return this.findFlightsByAirportsCode(searchParam, limit, offset);
        } else {
            return this.findFlightsByAirportsCodeAndDates(searchParam, limit, offset);
        }
    }

    @Override
    public int getFlightsCountByAirportsCodeOrDates(FlightSearchParam searchParam) throws IllegalAccessException {
        if (StringUtils.isAnyNullOrEmpty(searchParam.getDepartureDate(), searchParam.getArrivalDate())) {
            return this.getFlightsCountByAirportsCode(searchParam);
        } else {
            return this.getFlightsCountByAirportsCodeAndDates(searchParam);
        }
    }

    private List<Flights> findFlightsByAirportsCodeAndDates(FlightSearchParam searchParam, int limit, int offset) throws IllegalAccessException {
        List<Flights> result = new ArrayList<>();

        String sql = " SELECT f.flight_no," +
                " f.departure_airport," +
                " f.arrival_airport," +
                " f.scheduled_departure," +
                " f.scheduled_arrival," +
                " f.actual_departure," +
                " f.actual_arrival," +
                " f.aircraft_code," +
                " ad.model ->> ? AS aircraft_model " +
                " FROM flights f, aircrafts_data ad " +
                " WHERE f.aircraft_code = ad.aircraft_code AND " +
                "       (departure_airport=? AND  scheduled_departure > ? AND scheduled_departure <= ?) " +
                "       OR (arrival_airport=? AND  scheduled_arrival > ? AND scheduled_arrival <= ?) LIMIT ? OFFSET ?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, searchParam.getLang().getTextCode());
                ps.setString(2, searchParam.getDepartureAirport());
                ps.setTimestamp(3, Timestamp.valueOf(LocalDate.parse(searchParam.getDepartureDate()).atTime(00, 00, 00)));
                ps.setTimestamp(4, Timestamp.valueOf(LocalDate.parse(searchParam.getDepartureDate()).atTime(23, 59, 59)));
                ps.setString(5, searchParam.getArrivalAirport());
                ps.setTimestamp(6, Timestamp.valueOf(LocalDate.parse(searchParam.getArrivalDate()).atTime(00, 00, 00)));
                ps.setTimestamp(7, Timestamp.valueOf(LocalDate.parse(searchParam.getArrivalDate()).atTime(23, 59, 59)));
                ps.setInt(8, limit);
                ps.setInt(9, offset);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Flights flights = new Flights();
                        flights.setFlightNo(rs.getString("flight_no"));
                        flights.setScheduledDeparture(rs.getObject("scheduled_departure", OffsetDateTime.class));
                        flights.setScheduledArrival(rs.getObject("scheduled_arrival", OffsetDateTime.class));
                        flights.setActualDeparture(rs.getObject("actual_departure", OffsetDateTime.class));
                        flights.setActualArrival(rs.getObject("actual_arrival", OffsetDateTime.class));
                        flights.setAircraftModel(rs.getString("aircraft_model"));

                        result.add(flights);
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalAccessException("Ошибка работы с базой данных");
        }

        return result;
    }

    private List<Flights> findFlightsByAirportsCode(FlightSearchParam searchParam, int limit, int offset) throws IllegalAccessException {
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
                ps.setString(1, searchParam.getLang().getTextCode());
                ps.setString(2, searchParam.getDepartureAirport());
                ps.setString(3, searchParam.getArrivalAirport());
                ps.setInt(4, limit);
                ps.setInt(5, offset);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Flights flights = new Flights();
                        flights.setFlightNo(rs.getString("flight_no"));
                        flights.setScheduledDeparture(rs.getObject("scheduled_departure", OffsetDateTime.class));
                        flights.setScheduledArrival(rs.getObject("scheduled_arrival", OffsetDateTime.class));
                        flights.setActualDeparture(rs.getObject("actual_departure", OffsetDateTime.class));
                        flights.setActualArrival(rs.getObject("actual_arrival", OffsetDateTime.class));
                        flights.setAircraftModel(rs.getString("aircraft_model"));

                        result.add(flights);
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalAccessException("Ошибка работы с базой данных");
        }

        return result;
    }

    private int getFlightsCountByAirportsCode(FlightSearchParam searchParam) throws IllegalAccessException {
        int result = 0;
        String sql = "SELECT count(*) FROM flights WHERE departure_airport=? AND arrival_airport=?";

        try (Connection connection = dataSource.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, searchParam.getDepartureAirport());
                ps.setString(2, searchParam.getArrivalAirport());

                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    result = rs.getInt("count");
                }
            }
        } catch (SQLException e) {
            throw new IllegalAccessException("Ошибка работы с базой данных");
        }

        return result;
    }

    private int getFlightsCountByAirportsCodeAndDates(FlightSearchParam searchParam) throws IllegalAccessException {
        int result = 0;
        String sql = " SELECT count(*)" +
                " FROM flights" +
                " WHERE (departure_airport=? AND  scheduled_departure > ? AND scheduled_departure <= ?) " +
                "       OR (arrival_airport=? AND  scheduled_arrival > ? AND scheduled_arrival <= ?)";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, searchParam.getDepartureAirport());
                ps.setTimestamp(2, Timestamp.valueOf(LocalDate.parse(searchParam.getDepartureDate()).atTime(0, 0, 0)));
                ps.setTimestamp(3, Timestamp.valueOf(LocalDate.parse(searchParam.getDepartureDate()).atTime(23, 59, 59)));
                ps.setString(4, searchParam.getArrivalAirport());
                ps.setTimestamp(5, Timestamp.valueOf(LocalDate.parse(searchParam.getArrivalDate()).atTime(0, 0, 0)));
                ps.setTimestamp(6, Timestamp.valueOf(LocalDate.parse(searchParam.getArrivalDate()).atTime(23, 59, 59)));

                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    result = rs.getInt("count");
                }
            }
        } catch (SQLException e) {
            throw new IllegalAccessException("Ошибка работы с базой данных");
        }

        return result;
    }

}
