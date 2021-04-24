package by.it_academy.jd2.airports.service;

import by.it_academy.jd2.airports.core.dto.FlightSearchParam;
import by.it_academy.jd2.airports.core.dto.Flights;
import by.it_academy.jd2.airports.core.dto.FlightsPageParam;
import by.it_academy.jd2.airports.dao.nativesql.FlightsDao;
import by.it_academy.jd2.airports.dao.api.IFlightsDao;
import by.it_academy.jd2.airports.service.api.IFlightsSearcherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FlightsSearcherServiceTest {
    private final IFlightsDao mockFlightsDao = mock(FlightsDao.class);
    private final IFlightsSearcherService service = FlightsSearcherService.getInstance();

    FlightsSearcherServiceTest() throws IllegalAccessException {
        try{
            Field field = FlightsSearcherService.class.getDeclaredField("flightsDao");
            field.setAccessible(true);
            field.set(service, mockFlightsDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void findFlights_EmptyParams_IllegalArgumentException() {
        FlightSearchParam searchParam = new FlightSearchParam();
        FlightsPageParam pageParam = new FlightsPageParam();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> service.findFlights(searchParam, pageParam),
                "Заполните поля Аэропорт вылета и Аэропорт прилета!");
    }

    @Test
    void findFlights_EmptyPageParam_ResultList() throws IllegalAccessException {
        FlightSearchParam searchParam = new FlightSearchParam();
        searchParam.setDepartureAirport("DME");
        searchParam.setArrivalAirport("DME");
        FlightsPageParam pageParam = new FlightsPageParam();

        when(mockFlightsDao.getFlightsCountByAirportsCodeOrDates(searchParam)).thenReturn(50);
        when(mockFlightsDao.findFlightsByAirportsOrDates(searchParam, 25, 0)).thenReturn(null);

        List<Flights> flights = service.findFlights(searchParam, pageParam);

        Assertions.assertEquals(pageParam.getPageItemLimit(), 25);
        Assertions.assertEquals(pageParam.getFlightsTotalCount(), 50);
        Assertions.assertEquals(pageParam.getTotalPages(), 2);
    }


    @Test
    void findFlights_FlightNotFound_ResultNull() throws IllegalAccessException {
        FlightSearchParam searchParam = new FlightSearchParam();
        searchParam.setDepartureAirport("DME");
        searchParam.setArrivalAirport("DME");
        FlightsPageParam pageParam = new FlightsPageParam();
        pageParam.setPageItemLimit(25);

        when(mockFlightsDao.getFlightsCountByAirportsCodeOrDates(searchParam)).thenReturn(0);

        Assertions.assertNull(service.findFlights(searchParam, pageParam));
    }


    @Test
    void findFlights_ResultList() throws IllegalAccessException {
        FlightSearchParam searchParam = new FlightSearchParam();
        searchParam.setDepartureAirport("DME");
        searchParam.setArrivalAirport("DME");
        FlightsPageParam pageParam = new FlightsPageParam();
        pageParam.setPageItemLimit(25);
        pageParam.setPageNo(1);

        List<Flights> result = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            result.add(new Flights());
        }

        when(mockFlightsDao.getFlightsCountByAirportsCodeOrDates(searchParam)).thenReturn(50);
        when(mockFlightsDao.findFlightsByAirportsOrDates(searchParam, 25, 0)).thenReturn(result);

        Assertions.assertEquals(service.findFlights(searchParam, pageParam).size(), 24);
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

        when(mockFlightsDao.getFlightsCountByAirportsCodeOrDates(searchParam)).thenReturn(2);

        Assertions.assertEquals(service.getFlightsCount(searchParam), 2);
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