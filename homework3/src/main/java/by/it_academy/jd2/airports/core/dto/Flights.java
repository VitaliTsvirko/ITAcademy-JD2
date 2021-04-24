package by.it_academy.jd2.airports.core.dto;

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
    private String flightNo;

    /**
     * Расписание вылета
     */
    private OffsetDateTime scheduledDeparture;

    /**
     * Расписание прилета
     */
    private OffsetDateTime scheduledArrival;

    /**
     * Актуальное время вылета
     */
    private OffsetDateTime actualDeparture;

    /**
     * Актуальное время прилета
     */
    private OffsetDateTime actualArrival;

    /**
     * Модель самолета
     */
    private String aircraftModel;

    /**
     *
     */

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
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

    public String getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(String aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "flight_no='" + flightNo + '\'' +
                ", scheduled_departure=" + scheduledDeparture +
                ", scheduled_arrival=" + scheduledArrival +
                ", actual_departure=" + actualDeparture +
                ", actual_arrival=" + actualArrival +
                ", aircraft_model='" + aircraftModel + '\'' +
                '}';
    }
}
