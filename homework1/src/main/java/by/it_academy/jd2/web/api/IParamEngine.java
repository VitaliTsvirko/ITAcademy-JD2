package by.it_academy.jd2.web.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Данный интерфейс предназначен для обработки параметров переданных в запросе
 *
 * @author Vitali Tsvirko
 */
public interface IParamEngine {
    String getParamValue(String paramName, HttpServletRequest req, HttpServletResponse resp);
}
