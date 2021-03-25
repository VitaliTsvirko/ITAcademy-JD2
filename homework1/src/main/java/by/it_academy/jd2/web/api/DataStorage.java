package by.it_academy.jd2.web.api;

import by.it_academy.jd2.web.utils.CookieParamEngine;
import by.it_academy.jd2.web.utils.SessionParamEngine;

public enum DataStorage {
    COOKIE (new CookieParamEngine()),
    SESSION (new SessionParamEngine());

    private final IParamEngine paramEngine;

    private DataStorage(IParamEngine paramEngine){
        this.paramEngine = paramEngine;
    }

    public IParamEngine getParamEngine(){
        return this.paramEngine;
    }
}
