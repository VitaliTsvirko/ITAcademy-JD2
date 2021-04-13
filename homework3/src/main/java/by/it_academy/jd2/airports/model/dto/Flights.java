package by.it_academy.jd2.airports.model.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Данный класс переназначен для хранения результат поиска рейсов
 *
 * @author Vitali Tsvirko
 */
public class Flights implements Serializable {
    /**
     * Номер рейса
     */
    private String flight_no;

    /**
     * Расписание вылета
     */
    private OffsetDateTime scheduled_departure;

    /**
     * Расписание прилета
     */
    private OffsetDateTime scheduled_arrival;

    /**
     * Актуальное время вылета
     */
    private OffsetDateTime actual_departure;

    /**
     * Актуальное время прилета
     */
    private OffsetDateTime actual_arrival;

    /**
     * Модель самолета
     */
    private String aircraft_model;

    /**
     *
     */

    public String getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(String flight_no) {
        this.flight_no = flight_no;
    }

    public OffsetDateTime getScheduled_departure() {
        return scheduled_departure;
    }

    public void setScheduled_departure(OffsetDateTime scheduled_departure) {
        this.scheduled_departure = scheduled_departure;
    }

    public OffsetDateTime getScheduled_arrival() {
        return scheduled_arrival;
    }

    public void setScheduled_arrival(OffsetDateTime scheduled_arrival) {
        this.scheduled_arrival = scheduled_arrival;
    }

    public OffsetDateTime getActual_departure() {
        return actual_departure;
    }

    public void setActual_departure(OffsetDateTime actual_departure) {
        this.actual_departure = actual_departure;
    }

    public OffsetDateTime getActual_arrival() {
        return actual_arrival;
    }

    public void setActual_arrival(OffsetDateTime actual_arrival) {
        this.actual_arrival = actual_arrival;
    }

    public String getAircraft_model() {
        return aircraft_model;
    }

    public void setAircraft_model(String aircraft_model) {
        this.aircraft_model = aircraft_model;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "flight_no='" + flight_no + '\'' +
                ", scheduled_departure=" + scheduled_departure +
                ", scheduled_arrival=" + scheduled_arrival +
                ", actual_departure=" + actual_departure +
                ", actual_arrival=" + actual_arrival +
                ", aircraft_model='" + aircraft_model + '\'' +
                '}';
    }
}
