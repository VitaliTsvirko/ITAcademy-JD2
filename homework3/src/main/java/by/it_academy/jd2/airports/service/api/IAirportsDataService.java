package by.it_academy.jd2.airports.service.api;

import by.it_academy.jd2.airports.model.dto.AirportsData;
import by.it_academy.jd2.airports.model.dto.Lang;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface IAirportsDataService {
    List<AirportsData> getAllAirportsData(Lang lang) throws ClassNotFoundException, SQLException, PropertyVetoException;
    Map<String, String> getAllAirportsCodeAndName(Lang lang) throws ClassNotFoundException, SQLException, PropertyVetoException;
}
