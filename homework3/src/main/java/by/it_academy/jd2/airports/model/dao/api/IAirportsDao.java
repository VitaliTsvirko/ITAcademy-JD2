package by.it_academy.jd2.airports.model.dao.api;

import by.it_academy.jd2.airports.model.dto.AirportsData;
import by.it_academy.jd2.airports.model.dto.Lang;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IAirportsDao {
    List<AirportsData> getAllAirportsData(Lang lang) throws SQLException;
    Map<String, String> getAllAirportsCodeAndName(Lang lang) throws SQLException;

}
