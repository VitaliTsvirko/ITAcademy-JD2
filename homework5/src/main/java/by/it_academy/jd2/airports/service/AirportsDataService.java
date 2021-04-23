package by.it_academy.jd2.airports.service;

import by.it_academy.jd2.airports.dao.AirportsDao;
import by.it_academy.jd2.airports.dao.api.IAirportsDao;
import by.it_academy.jd2.airports.core.dto.AirportsData;
import by.it_academy.jd2.airports.core.dto.Lang;
import by.it_academy.jd2.airports.dao.hibernate.AirportsHibernateDao;
import by.it_academy.jd2.airports.service.api.IAirportsDataService;

import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Map;

/**
 * Данный класс является сервисом и предназначен для получения информации об аэропортах
 *
 * @author Vitali Tsvirko
 * @see AirportsData
 * @see AirportsDao
 */
public class AirportsDataService implements IAirportsDataService {
    private static volatile AirportsDataService instance;
    private final IAirportsDao airportDao;

    private AirportsDataService() throws IllegalAccessException {
         airportDao = new AirportsHibernateDao();
    }

    /**
     * Данный метод метод предназначен для создания и возвращения объекта
     * <p>Если объект еще не создан, то создаться новый объект {@code AirportsDataService} и возвращается</p>
     * <p>Если объект был создан ранее, то он и возвращается</p>
     * @return возвращает объект {@code AirportsDataService}
     */
    public static AirportsDataService getInstance() throws IllegalAccessException {
        if (instance == null) {
            synchronized (AirportsDataService.class){
                if (instance == null) {
                    try {
                        instance = new AirportsDataService();
                    } catch (IllegalArgumentException e) {
                        throw new IllegalAccessException("Ошибка сервиса");
                    }
                }
            }
        }
        return instance;
    }

    /**
     * Данный метод предназначен для получения списка всех аэропортов
     * @param lang язык на котором возвращаются названия аэропорта и города
     * @return список всех аэропортов
     * @throws IllegalAccessException если произошла ошибка в драйвере работы с базой или сервисом
     */
    @Override
    public List<AirportsData> getAllAirportsData(Lang lang) throws IllegalAccessException {
        return airportDao.getAllAirportsData(lang);
    }

    /**
     * Возвращает {@code Map} где клюем является код аэропорта, а значением название аэропорта.
     * @param lang язык на котором возвращаются названия аэропорта
     * @return возвращает {@code Map} всех аэропортов
     * @throws IllegalAccessException если произошла ошибка в драйвере работы с базой или сервисом
     */
    @Override
    public Map<String, String> getAllAirportsCodeAndName(Lang lang) throws IllegalAccessException {
        return airportDao.getAllAirportsCodeAndName(lang);
    }

}
