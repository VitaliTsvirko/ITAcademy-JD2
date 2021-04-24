package by.it_academy.jd2.airports.service.api;

import by.it_academy.jd2.airports.core.dto.AirportsData;
import by.it_academy.jd2.airports.core.dto.Lang;

import java.util.List;
import java.util.Map;


public interface IAirportsDataService {
    List<AirportsData> getAllAirportsData(Lang lang) throws IllegalAccessException;
    Map<String, String> getAllAirportsCodeAndName(Lang lang) throws IllegalAccessException;
}
