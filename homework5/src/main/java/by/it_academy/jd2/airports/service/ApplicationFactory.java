package by.it_academy.jd2.airports.service;

import by.it_academy.jd2.airports.dao.api.IAirportsDao;
import by.it_academy.jd2.airports.dao.api.IFlightsDao;
import by.it_academy.jd2.airports.dao.hibernate.AirportsHibernateDao;
import by.it_academy.jd2.airports.dao.hibernate.FlightsHibernateDao;


/**
 * @author Vitali Tsvirko
 */
public class ApplicationFactory {
    public static IFlightsDao getFlightDao(){
        return new FlightsHibernateDao();
    }

    public static IAirportsDao getAirportsDao(){
        return new AirportsHibernateDao();
    }

}
