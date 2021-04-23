package by.it_academy.jd2.airports.core.dto;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Данный класс переназначен для хранения данных о аэропортах
 *
 * @author Vitali Tsvirko
 */
@Entity
@Table(name = "airports")
public class AirportsData implements Serializable {
    /**
     * Код аэропорта
     */
    @Id
    @Column(name = "airport_code", length=3, columnDefinition="bpchar")
    private String airportCode;

    /**
     * Название аэропорта
     */
    @Column(name = "airport_name")
    private String airportName;

    /**
     * Город
     */
    @Column(name = "city")
    private String city;

    /**
     * Координаты
     */
    @Column(name = "coordinates", columnDefinition = "point")
    //@Type(type = "org.hibernate.spatial.GeometryType")
    private String coordinates;

    /**
     * Часовой пояс
     */
    @Column(name = "timezone")
    private String timezone;


    @Transient
    private final Coordinates coordinatesPoints = new Coordinates();

    public Double getCoordinateX() {
        return coordinatesPoints.x;
    }

    public Double getCoordinateY() {
        return coordinatesPoints.y;
    }

    public void setCoordinatesPoints(double x, double y) {
        this.coordinatesPoints.setX(x);
        this.coordinatesPoints.setY(y);
    }

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

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String point) {
        this.coordinates = point;
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
                ", coordinates=" + coordinates.toString() +
                ", timezone='" + timezone + '\'' +
                '}';
    }

    private class Coordinates implements Serializable{
        private double x;
        private double y;

        public Coordinates(){

        }

        public Coordinates(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }
}
