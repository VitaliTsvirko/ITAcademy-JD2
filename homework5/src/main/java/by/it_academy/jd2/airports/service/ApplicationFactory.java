package by.it_academy.jd2.airports.service;

import by.it_academy.jd2.airports.dao.api.IAirportsDao;
import by.it_academy.jd2.airports.dao.api.IFlightsDao;
import by.it_academy.jd2.airports.dao.hibernate.AirportsHibernateDao;
import by.it_academy.jd2.airports.dao.hibernate.FlightsHibernateDao;
import by.it_academy.jd2.airports.dao.nativesql.AirportsDao;
import by.it_academy.jd2.airports.dao.nativesql.FlightsDao;

import java.beans.PropertyVetoException;


/**
 * @author Vitali Tsvirko
 */
public class ApplicationFactory {
    public static IFlightsDao getFlightDao(){
        return new FlightsHibernateDao();
       /*try {
            return new FlightsDao();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        return null;*/
    }

    public static IAirportsDao getAirportsDao(){
        return new AirportsHibernateDao();
/*        try {
            return new AirportsDao();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return null;*/

    }

}
