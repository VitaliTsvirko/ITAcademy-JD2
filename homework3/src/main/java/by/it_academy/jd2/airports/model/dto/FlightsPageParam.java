package by.it_academy.jd2.airports.model.dto;

import java.io.Serializable;

/**
 * Данный класс переназначен для хранения параметров страницы с данными
 * о рейсах и используется для пагинации данных о рейсах.
 *
 * @author Vitali Tsvirko
 */
public class FlightsPageParam implements Serializable {
    /**
     * Количество рейсов найденных в базе
     */
    private Integer flightsTotalCount;

    /**
     * Номер станицы
     */
    private Integer pageNo;

    /**
     * Количество элементов выводим на одной странице
     */
    private Integer pageItemLimit;

    /**
     * Количество страниц
     */
    private Integer totalPages;

    public Integer getFlightsTotalCount() {
        return flightsTotalCount;
    }

    public void setFlightsTotalCount(Integer flightsTotalCount) {
        this.flightsTotalCount = flightsTotalCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageItemLimit() {
        return pageItemLimit;
    }

    public void setPageItemLimit(Integer pageItemLimit) {
        this.pageItemLimit = pageItemLimit;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "FlightsPageParam{" +
                "flightsTotalCount=" + flightsTotalCount +
                ", pageNo=" + pageNo +
                ", pageItemLimit=" + pageItemLimit +
                ", totalPages=" + totalPages +
                '}';
    }
}
