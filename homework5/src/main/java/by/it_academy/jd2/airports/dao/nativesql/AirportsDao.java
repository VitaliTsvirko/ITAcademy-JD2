package by.it_academy.jd2.airports.dao.nativesql;

import by.it_academy.jd2.airports.core.dto.AirportsData;
import by.it_academy.jd2.airports.dao.api.IAirportsDao;
import by.it_academy.jd2.airports.dao.nativesql.core.ConnectionPoolCreator;
import by.it_academy.jd2.airports.core.dto.Lang;
import by.it_academy.jd2.airports.dao.hibernate.core.HibernateCreator;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.postgresql.geometric.PGpoint;

import javax.sql.DataSource;
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
 * @author Vitali Tsvirko
 */
public class AirportsDao implements IAirportsDao {
    private final DataSource dataSource = ConnectionPoolCreator.getInstance();

    public AirportsDao() throws PropertyVetoException {
    }

    @Override
    public List<AirportsData> getAllAirportsData(Lang lang) throws IllegalAccessException {
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
                        data.setCoordinatesPoints(pgPoint.x, pgPoint.y);

                        data.setTimezone(rs.getString("timezone"));

                        result.add(data);
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalAccessException("Ошибка работы с базой данных");
        }

        return result;
    }

    @Override
    public Map<String, String> getAllAirportsCodeAndName(Lang lang) throws IllegalAccessException {
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
            throw new IllegalAccessException("Ошибка работы с базой данных");
        }

        return result;
    }

}
