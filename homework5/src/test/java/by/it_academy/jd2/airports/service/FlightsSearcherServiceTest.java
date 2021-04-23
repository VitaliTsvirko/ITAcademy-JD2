package by.it_academy.jd2.airports.service;

import by.it_academy.jd2.airports.core.dto.FlightSearchParam;
import by.it_academy.jd2.airports.dao.nativesql.FlightsDao;
import by.it_academy.jd2.airports.dao.api.IFlightsDao;
import by.it_academy.jd2.airports.service.api.IFlightsSearcherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.Mockito.mock;

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