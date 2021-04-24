package by.it_academy.jd2.airports.dao.hibernate;

import by.it_academy.jd2.airports.core.dto.AirportsData;
import by.it_academy.jd2.airports.core.dto.Lang;
import by.it_academy.jd2.airports.dao.api.IAirportsDao;
import by.it_academy.jd2.airports.dao.nativesql.AirportsDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AirportsHibernateDaoTest {
    private final IAirportsDao nativeSqlDao = new AirportsDao();
    private final IAirportsDao hibernateDao = new AirportsHibernateDao();

    AirportsHibernateDaoTest() throws PropertyVetoException {
    }

    @Test
    void getAllAirportsData_SameResult_SizeEquals() throws IllegalAccessException {
        List<AirportsData> allAirportsData1 = nativeSqlDao.getAllAirportsData(Lang.RU);
        List<AirportsData> allAirportsData2 = hibernateDao.getAllAirportsData(Lang.RU);

        Assertions.assertEquals(allAirportsData1.size(), allAirportsData2.size());
    }


    @Test
    void getAllAirportsCodeAndName_SameData_MapEquals() throws IllegalAccessException {
        Map<String, String> allAirportsCodeAndName1 = nativeSqlDao.getAllAirportsCodeAndName(Lang.RU);
        Map<String, String> allAirportsCodeAndName2 = hibernateDao.getAllAirportsCodeAndName(Lang.RU);

        assertEquals(allAirportsCodeAndName2, allAirportsCodeAndName1);
    }
}