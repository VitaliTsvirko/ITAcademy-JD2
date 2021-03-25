package by.it_academy.jd2.web.utils;

import by.it_academy.jd2.web.api.IParamEngine;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.concurrent.TimeUnit;

/**
 * Данный класс обрабатывает параметры GET запроса и данные сессии
 *
 * @author Vitali Tsvirko
 */
public class SessionParamEngine implements IParamEngine {

    /**
     * Данный метод обрабатывает параметры переданные в запросе.
     * Если в get запросе передан {@code paramName}, то возвращается значение переданное в запросе и данные записываются в сессию
     * Если в get запросе нет искомого параметра {@code paramName}, то выполняется поиск данных в сессии.
     * Если параметра {@code paramName} там нет то возвращается {@code IllegalArgumentException}
     * @param paramName имя параметра
     * @param req данные запроса
     * @param resp данные ответа
     * @return значение параметра {@code paramName}.
     *         Если данных нет вернет {@code IllegalArgumentException}
     */
    @Override
    public String getParamValue(String paramName, HttpServletRequest req, HttpServletResponse resp) {
        String value = req.getParameter(paramName);

        if (value == null){
            HttpSession session = req.getSession();

            if (!session.isNew()){
                value = (String) session.getAttribute(paramName);
            }
        }

        if (value == null){
            throw new IllegalArgumentException("Не перадан обязательный параметр " + paramName);
        }

        saveParamValue(paramName, value, req);

        return value;
    }

    /**
     * Данный метод добавляет cookie в ответ
     * @param paramName имя параметра
     * @param value значение параметра
     */
    public void saveParamValue(String paramName, String value, HttpServletRequest req){
        HttpSession session = req.getSession();
        session.setAttribute(paramName, value);
        session.setMaxInactiveInterval(Math.toIntExact(TimeUnit.HOURS.toSeconds(1)));
    }
}
