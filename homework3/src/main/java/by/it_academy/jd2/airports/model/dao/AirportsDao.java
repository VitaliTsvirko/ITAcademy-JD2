package by.it_academy.jd2.airports.model.dao;

import by.it_academy.jd2.airports.model.dao.core.ConnectionPoolCreator;
import by.it_academy.jd2.airports.model.dto.AirportsData;
import by.it_academy.jd2.airports.model.dto.Lang;
import org.postgresql.geometric.PGpoint;

import javax.sql.DataSource;
import java.awt.geom.Point2D;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Vitali Tsvirko
 */
public class AirportsDao {
    private final DataSource dataSource = ConnectionPoolCreator.getInstance();

    public AirportsDao() throws PropertyVetoException {
    }

    public List<AirportsData> getAllAirportsData(Lang lang) {
        List<AirportsData> result = new LinkedList<>();
        String sql = "SELECT ml.airport_code, ml.airport_name ->> ? AS airport_name, " +
                        "ml.city ->> ? AS city, ml.coordinates, ml.timezone " +
                        "FROM bookings.airports_data ml ORDER BY city";

        try (Connection connection = dataSource.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, lang.getTextCode());
                ps.setString(2, lang.getTextCode());

                try(ResultSet rs = ps.executeQuery()) {

                    PGpoint pgPoint;

                    while(rs.next()){
                        AirportsData data = new AirportsData();
                        data.setAirportCode(rs.getString("airport_code"));
                        data.setAirportName(rs.getString("airport_name"));
                        data.setCity(rs.getString("city"));

                        pgPoint = new PGpoint(rs.getString("coordinates"));

                        data.setCoordinates(new Point2D.Double(pgPoint.x, pgPoint.y));
                        data.setTimezone(rs.getString("timezone"));

                        result.add(data);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    public Map<String, String> getAllAirportsCodeAndName(Lang lang){
        Map<String, String> result = new LinkedHashMap<>();
        String sql = "SELECT ml.airport_code, ml.airport_name ->> ? AS airport_name " +
                     "FROM bookings.airports_data ml " +
                     "ORDER BY airport_name";

        try (Connection connection = dataSource.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, lang.getTextCode());

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        result.put(rs.getString("airport_code"), rs.getString("airport_name"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException, PropertyVetoException {
        AirportsDao airportDao = new AirportsDao();

        List<AirportsData> airportsData = airportDao.getAllAirportsData(Lang.RU);
        Map<String, String> airportsMap = airportDao.getAllAirportsCodeAndName(Lang.RU);

        System.out.println(airportsData.size());
        System.out.println(airportsMap.size());
    }

}
