package by.it_academy.jd2.airports.service;

import by.it_academy.jd2.airports.model.dao.AirportsDao;
import by.it_academy.jd2.airports.model.dao.TicketsDao;
import by.it_academy.jd2.airports.model.dto.AirportsData;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Vitali Tsvirko
 */
public class AirportsDataService {
    private static volatile AirportsDataService instance;
    private final AirportsDao airportDao;
    private final TicketsDao ticketsDao;

    private AirportsDataService() throws PropertyVetoException {
        airportDao = new AirportsDao();
        ticketsDao = new TicketsDao();
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

    public List<AirportsData> getAllAirportsData(String langCode) throws ClassNotFoundException, SQLException, PropertyVetoException {
        return airportDao.getAllAirportsData(langCode);
    }


    public Map<String, String> getAllAirportsCodeAndName(String langCode) throws ClassNotFoundException, SQLException, PropertyVetoException {
        return airportDao.getAllAirportsCodeAndName(langCode);
    }

}
