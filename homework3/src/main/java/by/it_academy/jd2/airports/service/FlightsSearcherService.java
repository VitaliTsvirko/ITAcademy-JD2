package by.it_academy.jd2.airports.service;

import by.it_academy.jd2.airports.core.utils.StringUtils;
import by.it_academy.jd2.airports.dao.FlightsDao;
import by.it_academy.jd2.airports.dao.api.IFlightsDao;
import by.it_academy.jd2.airports.core.dto.*;
import by.it_academy.jd2.airports.service.api.IFlightsSearcherService;

import java.time.LocalDate;
import java.util.List;

/**
 * Данный класс является сервисом и предназначен для получения информации о рейсах
 * <p>Выполняет поиск по заданным параметрам переданным в переменной {@code FlightSearchParam searchParam}
 * Параметры вывода передаются в переменной {@code FlightsPageParam pageParam}
 * </p>
 *
 * @author Vitali Tsvirko
 * @see FlightSearchParam
 * @see FlightsPageParam
 */
public class FlightsSearcherService implements IFlightsSearcherService {
    private static volatile FlightsSearcherService instance;
    private IFlightsDao flightsDao;

    private FlightsSearcherService() {
        flightsDao = new FlightsDao();
    }

    /**
     * Данный метод метод предназначен для создания и возвращения объекта
     * <p>Если объект еще не создан, то создаться новый объект {@code FlightsSearcherService} и возвращается</p>
     * <p>Если объект был создан ранее, то он и возвращается</p>
     * @return возвращает объект {@code FlightsSearcherService}
     */
    public static FlightsSearcherService getInstance() {
        if (instance == null) {
            synchronized (FlightsSearcherService.class){
                if (instance == null) {
                        instance = new FlightsSearcherService();
                    }
                }
            }
        return instance;
    }

    /**
     * Данный метод выполняет поиск рейсов по заданным параметрам переданным в переменной {@code FlightSearchParam searchParam}
     *  * Параметры вывода передаются в переменной {@code FlightsPageParam pageParam}
     * @param lang язык на котором возвращаются названия
     * @param searchParam параметры поиска
     * @param pageParam параметры вывода данных на страницу
     * @return список рейсов {@code Flights} соответствущий заданных параметрам.
     *          Если ничего не найдено вернет {@code null}
     * @throws IllegalAccessException если произошла ошибка работы с базой
     * @throws IllegalArgumentException если переданы неверные параметры поиска
     * @see FlightSearchParam
     * @see FlightsPageParam
     */
    @Override
    public List<Flights> findFlights(Lang lang, FlightSearchParam searchParam, FlightsPageParam pageParam) throws IllegalArgumentException, IllegalAccessException {
        validateSearchParam(searchParam);

        int flightsTotalCount = getFlightsCount(searchParam);
        pageParam.setFlightsTotalCount(flightsTotalCount);

        if (flightsTotalCount > 0){
            int totalPages = (int) Math.ceil(flightsTotalCount * 1.0 / pageParam.getPageItemLimit());
            int pageNo = validatePageNo(searchParam.getQueryPageNo(), totalPages);
            int offset = requestOffsetCalc(flightsTotalCount, pageParam.getPageItemLimit(), pageNo);

            pageParam.setFlightsTotalCount(flightsTotalCount);
            pageParam.setTotalPages(totalPages);
            pageParam.setPageNo(pageNo);

            if (StringUtils.isAnyNullOrEmpty(searchParam.getDepartureDate(), searchParam.getArrivalDate())){
                return flightsDao.getFlightsByAirportsCode(lang, searchParam.getDepartureAirport(), searchParam.getArrivalAirport(), pageParam.getPageItemLimit(), offset);
            } else {
                return flightsDao.getFlightsByAirportsCodeAndDates(lang, searchParam.getDepartureAirport(), searchParam.getArrivalAirport(), LocalDate.parse(searchParam.getDepartureDate()), LocalDate.parse(searchParam.getDepartureDate()), pageParam.getPageItemLimit(), offset);
            }
        }

        return null;
    }


    /**
     * Данный метод предназначен для определения количества рейсов по заданным параметрам.
     *
     * @param searchParam параметры поиска
     * @return количество рейсов соответствующих критериям поиска.
     *         если ничего не найдено вернет ноль
     * @throws IllegalAccessException если произошла ошибка работы с базой данных
     * @see FlightSearchParam
     */
    @Override
    public int getFlightsCount (FlightSearchParam searchParam) throws IllegalAccessException {
        validateSearchParam(searchParam);

        if (StringUtils.isAnyNullOrEmpty(searchParam.getDepartureDate(), searchParam.getArrivalDate())){
            return flightsDao.getFlightsCountByAirportsCode(searchParam.getDepartureAirport(), searchParam.getArrivalAirport());
        } else {
            return flightsDao.getFlightsCountByAirportsCodeAndDates(searchParam.getDepartureAirport(), searchParam.getArrivalAirport(), LocalDate.parse(searchParam.getDepartureDate()), LocalDate.parse(searchParam.getDepartureDate()));
        }
    }


    /**
     * Данный метод выполняет проверку параметров поиска.
     *
     * @param searchParam параметры поиска
     * @throws IllegalArgumentException если есть ошибка в параметрах заданных поиска
     */
    private void validateSearchParam(FlightSearchParam searchParam) throws IllegalArgumentException{
        if (StringUtils.isAnyNullOrEmpty(searchParam.getDepartureAirport(), searchParam.getArrivalAirport())){
            throw new IllegalArgumentException("Заполните поля Аэропорт вылета и Аэропорт прилета!");
        }

        if (StringUtils.isAnyNullOrEmpty(searchParam.getDepartureDate(), searchParam.getArrivalDate())){
            if (StringUtils.isAnyNotNullOrEmpty(searchParam.getDepartureDate(), searchParam.getArrivalDate())){
                throw new IllegalArgumentException("Заполните поля Дата вылета и Дата прилета, или выполните поиск без дат");
            }
        }
    }


    /**
     * Данный метод выполняет проверку правильности номера страницы
     * @param requestPageNo номер страницы переданный в запросе
     * @param totalPages общее количество страницы
     * @return корректный номер страницы
     */
    private Integer validatePageNo(String requestPageNo, int totalPages) {
        int result = 1;

        if (requestPageNo != null) {
            try{
                int tmp = Integer.parseInt(requestPageNo);
                result = (tmp <= 0) ? 1 : tmp;
            } catch (NumberFormatException e){
                result = 1;
            }
        }

        return Math.min(result, totalPages);
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
