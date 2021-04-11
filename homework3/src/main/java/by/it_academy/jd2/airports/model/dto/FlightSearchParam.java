package by.it_academy.jd2.airports.model.dto;

import java.io.Serializable;

/**
 * Created by Vitali Tsvirko
 */
public class FlightSearchParam implements Serializable {
    private String departureAirport;
    private String arrivalAirport;
    private String departureDate;
    private String arrivalDate;
    private Integer pageNo;
    private Integer pageItemLimit;

    public Integer getPageItemLimit() {
        return pageItemLimit;
    }

    public void setPageItemLimit(Integer pageItemLimit) {
        this.pageItemLimit = pageItemLimit;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
       if (pageNo != null){
           this.pageNo = Integer.parseInt(pageNo);
           if (this.pageNo <= 0){
               this.pageNo = 1;
           }
       } else {
           this.pageNo = 1;
       }
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (departureAirport != null){
            sb.append("&departureAirport=").append(departureAirport);
        }
        if (arrivalAirport != null){
            sb.append("&arrivalAirport=").append(arrivalAirport);
        }
        if (departureDate != null){
            sb.append("&departureDate=").append(departureDate);
        }
        if (arrivalDate != null){
            sb.append("&arrivalDate=").append(arrivalDate);
        }

        return  sb.toString();
    }
}
