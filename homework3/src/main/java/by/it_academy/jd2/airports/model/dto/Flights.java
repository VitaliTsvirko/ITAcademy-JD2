package by.it_academy.jd2.airports.model.dto;

import java.io.Serializable;

/**
 * Created by Vitali Tsvirko
 */
public class Flights implements Serializable {
    private String flight_no;
    private String departure_airport;
    private String arrival_airport;
    private String scheduled_departure;
    private String scheduled_arrival;

    public String getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(String flight_no) {
        this.flight_no = flight_no;
    }

    public String getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(String departure_airport) {
        this.departure_airport = departure_airport;
    }

    public String getArrival_airport() {
        return arrival_airport;
    }

    public void setArrival_airport(String arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public String getScheduled_departure() {
        return scheduled_departure;
    }

    public void setScheduled_departure(String scheduled_departure) {
        this.scheduled_departure = scheduled_departure;
    }

    public String getScheduled_arrival() {
        return scheduled_arrival;
    }

    public void setScheduled_arrival(String scheduled_arrival) {
        this.scheduled_arrival = scheduled_arrival;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "flight_no='" + flight_no + '\'' +
                ", departure_airport='" + departure_airport + '\'' +
                ", arrival_airport='" + arrival_airport + '\'' +
                ", scheduled_departure='" + scheduled_departure + '\'' +
                ", scheduled_arrival='" + scheduled_arrival + '\'' +
                '}';
    }
}
