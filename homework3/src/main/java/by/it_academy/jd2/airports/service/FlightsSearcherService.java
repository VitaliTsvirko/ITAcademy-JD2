package by.it_academy.jd2.airports.service;

import by.it_academy.jd2.airports.core.utils.StringUtils;
import by.it_academy.jd2.airports.model.dao.FlightsDao;
import by.it_academy.jd2.airports.model.dao.api.IFlightsDao;
import by.it_academy.jd2.airports.model.dto.FlightSearchParam;
import by.it_academy.jd2.airports.model.dto.Flights;
import by.it_academy.jd2.airports.model.dto.Lang;
import by.it_academy.jd2.airports.service.api.IFlightsSearcherService;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Vitali Tsvirko
 */
public class FlightsSearcherService implements IFlightsSearcherService {
    private static volatile FlightsSearcherService instance;
    private final IFlightsDao flightsDao;

    private FlightsSearcherService() throws PropertyVetoException {
        flightsDao = new FlightsDao();
    }

    /**
     * Данный метод метод предназначен для создания и возвращения объекта
     * <p>Если объект еще не создан, то создаться новый объект {@code UserService} и возвращается</p>
     * <p>Если объект был создан ранее, то он и возвращается</p>
     * @return возвращает объект {@code UserService}
     */
    public static FlightsSearcherService getInstance() {
        if (instance == null) {
            synchronized (FlightsSearcherService.class){
                if (instance == null) {
                    try {
                        instance = new FlightsSearcherService();
                    } catch (PropertyVetoException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

    @Override
    public List<Flights> findFlights(Lang lang, FlightSearchParam searchParam) throws SQLException, IllegalArgumentException {
        if (StringUtils.isAnyNullOrEmpty(searchParam.getDepartureAirport(), searchParam.getArrivalAirport())){
            throw new IllegalArgumentException("Заполните поля Аэропорт вылета и Аэропорт прилета!");
        }

        int flightsCount = getFlightsCount(searchParam);

        int offset = requestOffsetCalc(flightsCount, searchParam.getPageItemLimit(), searchParam.getPageNo());

        if (StringUtils.isAnyNullOrEmpty(searchParam.getDepartureDate(), searchParam.getArrivalDate())){
            return flightsDao.getFlightsByAirportsCode(lang, searchParam.getDepartureAirport(), searchParam.getArrivalAirport(), searchParam.getPageItemLimit(), offset);
        } else {
            return flightsDao.getFlightsByAirportsCodeAndDates(lang, searchParam.getDepartureAirport(), searchParam.getArrivalAirport(), LocalDate.parse(searchParam.getDepartureDate()), LocalDate.parse(searchParam.getDepartureDate()), searchParam.getPageItemLimit(), offset);
        }
    }


    @Override
    public int getFlightsCount (FlightSearchParam searchParam) throws SQLException {
        if (StringUtils.isAnyNullOrEmpty(searchParam.getDepartureAirport(), searchParam.getArrivalAirport())){
            throw new IllegalArgumentException("Заполните поля Аэропорт вылета и Аэропорт прилета!");
        }

        if (StringUtils.isAnyNullOrEmpty(searchParam.getDepartureDate(), searchParam.getArrivalDate())){
            return flightsDao.getFlightsCountByAirportsCode(searchParam.getDepartureAirport(), searchParam.getArrivalAirport());
        } else {
            return flightsDao.getFlightsCountByAirportsCodeAndDates(searchParam.getDepartureAirport(), searchParam.getArrivalAirport(), LocalDate.parse(searchParam.getDepartureDate()), LocalDate.parse(searchParam.getDepartureDate()));
        }
    }


    /**
     * Данный метод выполняет расчет смещения записей исходя из общего количества рейсов,
     * количества рейсов выводимых на страницу и номера страницы
     *
     * @param flightCount общее количество найденных билетов
     * @param pageLimit количество билетов выводимых на страницу
     * @param pageNo номер страницы
     * @return смещение (в количестве записей)
     *         <p>Если номера запрашиваемой страницы превышает общее количество страницы, то
     *         вернется значение смещения для последней возможной страницы</p>
     *         <p>Если номер запрашиваемой страницы меньше нуля то вернется ноль</p>
     */
    private int requestOffsetCalc (int flightCount, int pageLimit, int pageNo){
        if (pageNo <= 0) {
            return 0;
        }

        pageNo = pageNo - 1;

        int tmpOffset =pageLimit * pageNo;

        if (tmpOffset < flightCount) {
            return tmpOffset;
        } else {
            int pageCount = flightCount / pageLimit;

            return flightCount - (flightCount - pageCount * pageLimit);
        }
    }

}
