package by.it_academy.jd2.airports.core.dto;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * Данный класс переназначен для хранения данных о аэропортах
 *
 * @author Vitali Tsvirko
 */
public class AirportsData implements Serializable {
    /**
     * Код аэропорта
     */
    private String airportCode;

    /**
     * Название аэропорта
     */
    private String airportName;

    /**
     * Город
     */
    private String city;

    /**
     * Координаты
     */
    private Point2D coordinates;

    /**
     * Часовой пояс
     */
    private String timezone;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Point2D getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point2D coordinates) {
        this.coordinates = coordinates;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "AirportsData{" +
                "airportCode='" + airportCode + '\'' +
                ", airportName='" + airportName + '\'' +
                ", city='" + city + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
