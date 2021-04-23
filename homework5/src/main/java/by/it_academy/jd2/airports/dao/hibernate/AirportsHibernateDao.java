package by.it_academy.jd2.airports.dao.hibernate;

import by.it_academy.jd2.airports.core.dto.AirportsData;
import by.it_academy.jd2.airports.core.dto.Lang;
import by.it_academy.jd2.airports.dao.api.IAirportsDao;
import by.it_academy.jd2.airports.dao.hibernate.core.HibernateCreator;
import org.hibernate.Session;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;

/**
 * @author Vitali Tsvirko
 */
public class AirportsHibernateDao implements IAirportsDao {
    private final Session dataSource = HibernateCreator.getInstance().openSession();

    @Override
    public List<AirportsData> getAllAirportsData(Lang lang) throws IllegalAccessException {
        List<AirportsData> result;

        result = dataSource.createQuery("FROM AirportsData ORDER BY airportName").list();

        for (AirportsData airport : result) {
            String[] split = airport.getCoordinates()
                            .replace("(", "")
                            .replace(")", "")
                            .split(",");

            airport.setCoordinatesPoints(parseDouble(split[0]), parseDouble(split[1]));
        }

        return result;
    }

    @Override
    public Map<String, String> getAllAirportsCodeAndName(Lang lang) throws IllegalAccessException {
        Map<String, String> result = new LinkedHashMap<>();

        List<Object[]> list = dataSource.createQuery("SELECT airportCode, airportName FROM AirportsData ORDER BY airportName").list();

        for (Object[] o : list) {
            result.put((String) o[0], (String) o[1]);
        }


        return result;
    }


    public static void main(String[] args) throws IllegalAccessException {
        AirportsHibernateDao dao = new AirportsHibernateDao();

        List<AirportsData> allAirportsData = dao.getAllAirportsData(Lang.RU);
        Map<String, String> allAirportsCodeAndName = dao.getAllAirportsCodeAndName(Lang.RU);

        System.out.println(allAirportsCodeAndName.size());


    }
}
