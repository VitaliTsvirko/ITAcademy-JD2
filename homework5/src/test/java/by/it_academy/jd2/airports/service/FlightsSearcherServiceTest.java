package by.it_academy.jd2.airports.service;

import by.it_academy.jd2.airports.core.dto.FlightSearchParam;
import by.it_academy.jd2.airports.core.dto.Flights;
import by.it_academy.jd2.airports.core.dto.FlightsPageParam;
import by.it_academy.jd2.airports.core.dto.Lang;
import by.it_academy.jd2.airports.dao.FlightsDao;
import by.it_academy.jd2.airports.dao.api.IFlightsDao;
import by.it_academy.jd2.airports.service.api.IFlightsSearcherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FlightsSearcherServiceTest {
    private final IFlightsSearcherService service = FlightsSearcherService.getInstance();
    private final IFlightsDao mockFlightsDao = mock(FlightsDao.class);

    FlightsSearcherServiceTest() throws IllegalAccessException {
        try{
            Field flightsDao = FlightsSearcherService.class.getDeclaredField("flightsDao");
            flightsDao.setAccessible(true);
            flightsDao.set(service, mockFlightsDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getFlightsCount_EmptySearchParam_IllegalArgumentException() {
        FlightSearchParam searchParam = new FlightSearchParam();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> service.getFlightsCount(searchParam),
                "Заполните поля Аэропорт вылета и Аэропорт прилета!");
    }

    @Test
    void getFlightsCount_EmptyOneDateSearchParam_IllegalArgumentException() {
        FlightSearchParam searchParam = new FlightSearchParam();
        searchParam.setDepartureAirport("DME");
        searchParam.setArrivalAirport("DME");
        searchParam.setArrivalDate("2021-04-17");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> service.getFlightsCount(searchParam),
                "Заполните поля Дата вылета и Дата прилета, или выполните поиск без дат");

        searchParam.setArrivalDate(null);
        searchParam.setDepartureDate("2021-04-17");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> service.getFlightsCount(searchParam),
                "Заполните поля Дата вылета и Дата прилета, или выполните поиск без дат");
    }


    @Test
    void getFlightsCount_AirportsSearchParam_ResultOk() throws IllegalAccessException {
        FlightSearchParam searchParam = new FlightSearchParam();
        searchParam.setDepartureAirport("DME");
        searchParam.setArrivalAirport("DME");

        when(mockFlightsDao.getFlightsCountByAirportsCode("DME", "DME")).thenReturn(2);

        Assertions.assertEquals(service.getFlightsCount(searchParam), 2);
    }

    @Test
    void getFlightsCount_AirportsAndDatesSearchParam_ResultOk() throws IllegalAccessException {
        FlightSearchParam searchParam = new FlightSearchParam();
        searchParam.setDepartureAirport("DME");
        searchParam.setArrivalAirport("DME");
        searchParam.setDepartureDate("2021-12-14");
        searchParam.setArrivalDate("2021-12-13");

        when(mockFlightsDao.getFlightsCountByAirportsCodeAndDates("DME", "DME", LocalDate.parse("2021-12-14"), LocalDate.parse("2021-12-14"))).thenReturn(2);

        Assertions.assertEquals(service.getFlightsCount(searchParam), 2);
    }

    @Test
    void findFlights_pageParam_PageNumberFix() throws IllegalAccessException {
        FlightSearchParam searchParam = new FlightSearchParam();
        searchParam.setDepartureAirport("DME");
        searchParam.setArrivalAirport("DME");
        searchParam.setQueryPageNo("122");

        FlightsPageParam pageParam = new FlightsPageParam();
        pageParam.setPageItemLimit(25);

        when(mockFlightsDao.getFlightsCountByAirportsCode("DME", "DME")).thenReturn(2);

        List<Flights> mockFlights = new LinkedList<>();

        when(mockFlightsDao.getFlightsByAirportsCode(Lang.RU,"DME", "DME", 25, 0)).thenReturn(mockFlights);

        List<Flights> flights = service.findFlights(Lang.RU, searchParam, pageParam);

        Assertions.assertEquals(flights.size(), 0);

        searchParam.setQueryPageNo("-1");
        flights = service.findFlights(Lang.RU, searchParam, pageParam);

        Assertions.assertEquals(flights.size(), 0);

        when(mockFlightsDao.getFlightsCountByAirportsCode("DME", "DME")).thenReturn(49);
        searchParam.setQueryPageNo("2");
        flights = service.findFlights(Lang.RU, searchParam, pageParam);

        Assertions.assertEquals(flights.size(), 0);

    }

    @Test
    public void requestOffsetCalc() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = FlightsSearcherService.class.getDeclaredMethod("requestOffsetCalc", int.class, int.class, int.class);
        method.setAccessible(true);

        //Количнство элементов последней страницы меньше количества элементов на странице
        Assertions.assertEquals(method.invoke(service, 49,25,2), 25);

        //Номер страницы превышает количество страниц
        Assertions.assertEquals(method.invoke(service, 150,25,133), 150);

        //Отрицательный номер страницы
        Assertions.assertEquals(method.invoke(service, 150,25,-1), 0);

        //номер страницы равен нулю
        Assertions.assertEquals(method.invoke(service, 150,25,0), 0);

    }


    @Test
    public void validatePageNo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = FlightsSearcherService.class.getDeclaredMethod("validatePageNo", String.class, int.class);
        method.setAccessible(true);

        //requestPageNo == null
        Assertions.assertEquals(method.invoke(service, null,10), 1);

        //requestPageNo not number
        Assertions.assertEquals(method.invoke(service, "a",2), 1);

        //requestPageNo > totalPages
        Assertions.assertEquals(method.invoke(service, "10",2), 2);
    }






}