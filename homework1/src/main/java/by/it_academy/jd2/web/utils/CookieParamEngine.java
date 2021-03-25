package by.it_academy.jd2.web.utils;

import by.it_academy.jd2.web.api.IParamEngine;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vitali Tsvirko
 */
public class CookieParamEngine implements IParamEngine {

    /**
     * Данный метод обрабатывает параметры переданные в запросе.
     * Если в get запросе передан {@code paramName}, то возвращается значение переданное в запросе и данные записываются в cookie
     * Если в get запросе нет искомого параметра {@code paramName}, то выполняется поиск данных в cookie.
     * Если параметра {@code paramName} там нет то возвращается {@code null}
     * @param paramName имя параметра
     * @param req данные запроса
     * @param resp данные ответа
     * @return значение параметра {@code paramName} или {@code null} если данных нет
     */
    @Override
    public String getParamValue(String paramName, HttpServletRequest req, HttpServletResponse resp) {
        String value = req.getParameter(paramName);

        if (value == null){
            Cookie[] cookies = req.getCookies();

            if (cookies != null){
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(paramName)){
                        value = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                    }
                }
            }
        }

        if (value == null){
            throw new IllegalArgumentException("Не перадан обязательный параметр " + paramName);
        }

        saveParamValue(paramName, value, resp);

        return value;
    }

    /**
     * Данный метод добавляет cookie в ответ
     * @param paramName имя параметра
     * @param value значение параметра
     * @param resp ответ
     */
    public void saveParamValue(String paramName, String value, HttpServletResponse resp) {
        Cookie cookie = new Cookie(paramName, URLEncoder.encode(value, StandardCharsets.UTF_8));
        cookie.setMaxAge(Math.toIntExact(TimeUnit.HOURS.toSeconds(1)));

        resp.addCookie(cookie);
    }
}
