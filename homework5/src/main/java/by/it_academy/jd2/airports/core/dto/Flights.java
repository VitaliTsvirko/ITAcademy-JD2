package by.it_academy.jd2.airports.core.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Данный класс переназначен для хранения результат поиска рейсов
 *
 * @author Vitali Tsvirko
 */
@Entity
@Table(name = "flights_v")
public class Flights implements Serializable {

    @Id
    @Column(name = "flight_id")
    private Integer flightId;

    /**
     * Номер рейса
     */
    @Column(name = "flight_no", length=6, columnDefinition="bpchar")
    private String flightNo;

    @Column(name = "departure_airport", length=3, columnDefinition="bpchar")
    private String departureAirportCode;

    @Column(name = "arrival_airport", length=3, columnDefinition="bpchar")
    private String arrivalAirportCode;

    /**
     * Расписание вылета
     */
    @Column(name = "scheduled_departure")
    private OffsetDateTime scheduledDeparture;

    /**
     * Расписание прилета
     */
    @Column(name = "scheduled_arrival")
    private OffsetDateTime scheduledArrival;

    /**
     * Актуальное время вылета
     */
    @Column(name = "actual_departure")
    private OffsetDateTime actualDeparture;

    /**
     * Актуальное время прилета
     */
    @Column(name = "actual_arrival")
    private OffsetDateTime actualArrival;

    /**
     * Модель самолета
     */
    @Column(name = "aircraft_code", length=3, columnDefinition="bpchar")
    private String aircraftModelCode;

    /**
     *
     */


    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public OffsetDateTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(OffsetDateTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public OffsetDateTime getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(OffsetDateTime scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public OffsetDateTime getActualDeparture() {
        return actualDeparture;
    }

    public void setActualDeparture(OffsetDateTime actualDeparture) {
        this.actualDeparture = actualDeparture;
    }

    public OffsetDateTime getActualArrival() {
        return actualArrival;
    }

    public void setActualArrival(OffsetDateTime actualArrival) {
        this.actualArrival = actualArrival;
    }

    public String getAircraftModelCode() {
        return aircraftModelCode;
    }

    public void setAircraftModelCode(String aircraftModelCode) {
        this.aircraftModelCode = aircraftModelCode;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "flightId=" + flightId +
                ", flightNo='" + flightNo + '\'' +
                ", departureAirportCode='" + departureAirportCode + '\'' +
                ", arrivalAirportCode='" + arrivalAirportCode + '\'' +
                ", scheduledDeparture=" + scheduledDeparture +
                ", scheduledArrival=" + scheduledArrival +
                ", actualDeparture=" + actualDeparture +
                ", actualArrival=" + actualArrival +
                ", aircraftModelCode='" + aircraftModelCode + '\'' +
                '}';
    }
}
