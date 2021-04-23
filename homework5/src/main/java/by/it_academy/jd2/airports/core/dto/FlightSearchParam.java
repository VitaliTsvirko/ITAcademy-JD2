package by.it_academy.jd2.airports.core.dto;

import java.io.Serializable;

/**
 * Данный класс переназначен для хранения параметров поиска рейсов
 *
 * @author Vitali Tsvirko
 */
public class FlightSearchParam implements Serializable {
    /**
     * Аэропорт вылета
     */
    private String departureAirport;

    /**
     * Аэропорт прилета
     */
    private String arrivalAirport;

    /**
     * Дата вылета
     */
    private String departureDate;

    /**
     * Дата прилета
     */
    private String arrivalDate;

    /**
     * Номер страницы переданных в GET запросе
     */
    private String queryPageNo;


    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getQueryPageNo() {
        return queryPageNo;
    }

    public void setQueryPageNo(String queryPageNo) {
        this.queryPageNo = queryPageNo;
    }

    @Override
    public String toString() {
        return "FlightSearchParam{" +
                "departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", queryPageNo='" + queryPageNo + '\'' +
                '}';
    }
}
