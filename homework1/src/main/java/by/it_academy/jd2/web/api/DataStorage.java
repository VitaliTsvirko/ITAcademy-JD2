package by.it_academy.jd2.web.api;

import by.it_academy.jd2.web.utils.CookieParamEngine;
import by.it_academy.jd2.web.utils.SessionParamEngine;

/**
 *  Данный класс предназначен для хранения возможных вариантов хранилищ данных
 *  пользователя.
 *  <p>Реализованы следующие варианты хранилищ:</p>
 *  <ul>
 *      <li>COOKIE - хранение данных в cookie</li>
 *      <li>SESSION - хранение данных в сессии</li>
 *  </ul>
 *
 *  <p>Данный класс возвращения конкретную реализацию интерфейса {@code IParamEngine}</p>
 *
 *  @author Vitali Tsvirko
 *
 *  @see IParamEngine
 */
public enum DataStorage {
    COOKIE (new CookieParamEngine()),
    SESSION (new SessionParamEngine());

    private final IParamEngine paramEngine;

    DataStorage(IParamEngine paramEngine){
        this.paramEngine = paramEngine;
    }

    /**
     * Данный метод возвращения конкретную реализацию интерфейса {@code IParamEngine}
     * @return объект {@code IParamEngine}
     */
    public IParamEngine getParamEngine(){
        return this.paramEngine;
    }
}
