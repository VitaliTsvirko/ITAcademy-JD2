package by.it_academy.jd2.airports.service;

import by.it_academy.jd2.airports.core.dto.AirportsData;
import by.it_academy.jd2.airports.core.dto.Lang;
import by.it_academy.jd2.airports.dao.nativesql.AirportsDao;
import by.it_academy.jd2.airports.dao.api.IAirportsDao;
import by.it_academy.jd2.airports.service.api.IAirportsDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AirportsDataServiceTest {
    private final IAirportsDao mockAirportDao = mock(AirportsDao.class);
    private final IAirportsDataService airportsDataService = AirportsDataService.getInstance();

    AirportsDataServiceTest() throws IllegalAccessException {
        try{
            Field airportDao = AirportsDataService.class.getDeclaredField("airportDao");
            airportDao.setAccessible(true);
            airportDao.set(airportsDataService, mockAirportDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllAirportsCodeAndName() throws IllegalAccessException {
        Map<String, String> result = new HashMap<>();
        result.put("ABA", "Абакан");
        result.put("DYR", "Анадырь");

        when(mockAirportDao.getAllAirportsCodeAndName(Lang.RU)).thenReturn(result);
        Map<String, String> allAirportsCodeAndName = airportsDataService.getAllAirportsCodeAndName(Lang.RU);
        Assertions.assertEquals(allAirportsCodeAndName.size(), 2);
    }


    @Test
    void getAllAirportsData() throws IllegalAccessException {
        List<AirportsData> result = new LinkedList<>();
        result.add(new AirportsData());

        when(mockAirportDao.getAllAirportsData(Lang.RU)).thenReturn(result);
        List<AirportsData> allAirportsData = airportsDataService.getAllAirportsData(Lang.RU);
        Assertions.assertEquals(allAirportsData.size(), 1);
    }
}