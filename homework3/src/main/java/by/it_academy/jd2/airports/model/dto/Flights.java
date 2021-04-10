package by.it_academy.jd2.airports.model.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Created by Vitali Tsvirko
 */
public class Flights implements Serializable {
    private String flight_no;
    private OffsetDateTime scheduled_departure;
    private OffsetDateTime scheduled_arrival;
    private OffsetDateTime actual_departure;
    private OffsetDateTime actual_arrival;
    private String aircraft_model;
    private OffsetDateTime ts;


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
}
