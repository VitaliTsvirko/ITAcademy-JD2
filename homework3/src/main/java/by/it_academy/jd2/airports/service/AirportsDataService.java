package by.it_academy.jd2.airports.service;

import by.it_academy.jd2.airports.model.dao.AirportsDao;
import by.it_academy.jd2.airports.model.dao.FlightsDao;
import by.it_academy.jd2.airports.model.dao.api.IAirportsDao;
import by.it_academy.jd2.airports.model.dto.AirportsData;
import by.it_academy.jd2.airports.model.dto.Lang;
import by.it_academy.jd2.airports.service.api.IAirportsDataService;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Vitali Tsvirko
 */
public class AirportsDataService implements IAirportsDataService {
    private static volatile AirportsDataService instance;
    private final IAirportsDao airportDao;

    private AirportsDataService() throws PropertyVetoException {
        airportDao = new AirportsDao();
    }

    /**
     * Данный метод метод предназначен для создания и возвращения объекта
     * <p>Если объект еще не создан, то создаться новый объект {@code UserService} и возвращается</p>
     * <p>Если объект был создан ранее, то он и возвращается</p>
     * @return возвращает объект {@code UserService}
     */
    public static AirportsDataService getInstance() {
        if (instance == null) {
            synchronized (AirportsDataService.class){
                if (instance == null) {
                    try {
                        instance = new AirportsDataService();
                    } catch (PropertyVetoException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

    @Override
    public List<AirportsData> getAllAirportsData(Lang lang) throws ClassNotFoundException, SQLException, PropertyVetoException {
        return airportDao.getAllAirportsData(lang);
    }

    @Override
    public Map<String, String> getAllAirportsCodeAndName(Lang lang) throws ClassNotFoundException, SQLException, PropertyVetoException {
        return airportDao.getAllAirportsCodeAndName(lang);
    }

}
