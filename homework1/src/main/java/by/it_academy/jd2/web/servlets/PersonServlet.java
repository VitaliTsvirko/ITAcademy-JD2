package by.it_academy.jd2.web.servlets;

import by.it_academy.jd2.web.api.DataStorage;
import by.it_academy.jd2.core.dto.Person;
import by.it_academy.jd2.web.api.IParamEngine;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Данный сервлет предназначен для обработки GET запроса содержащего данные о пользователе
 * <p>Данные сохраняться в объект {@code Person} и выводятся в виде HTML</p>
 * <p>Имена параметров GET запроса заданы в файле web.xml </p>
 *
 * <p>Если параметры не были переданы в GET запросе они будут прочитаны из хранилища.</p>
 * <p>Хранилище задается в HTTP заголовке (параметр HEADER_CONFIG_NAME).</p>
 *
 *  @author Vitali Tsvirko
 *
 * @see DataStorage
 * @see Person
 */
@WebServlet(urlPatterns = "/person")
public class PersonServlet extends HttpServlet {
    private static final String FIRSTNAME_CONFIG_NAME = "FirstnameParamName";
    private static final String LASTNAME_CONFIG_NAME = "LastnameParamName";
    private static final String AGE_CONFIG_NAME = "AgeParamName";
    private static final String HEADER_CONFIG_NAME = "HeaderDataStorageParamName";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String dataStorage = req.getHeader(getServletContext().getInitParameter(HEADER_CONFIG_NAME));
        IParamEngine paramEngine;

        //Проверка заданного параметра хранилища
        try {
            paramEngine = DataStorage.valueOf(dataStorage).getParamEngine();
        } catch (IllegalArgumentException e){
            writer.write("<p><span style='color: red'>Задан неверный параметр хранилища данных!</span></p>");
            writer.write("<p><span style='color: gray'><i>DataStorage=" + dataStorage + "</span></p>");
            return;
        } catch (NullPointerException e){
            writer.write("<p><span style='color: red'>На задан параметр хранилища данных!</span></p>");
            return;
        }

        //Работа с данными запроса
        try{
            String firstName = paramEngine.getParamValue(getServletContext().getInitParameter(FIRSTNAME_CONFIG_NAME), req, resp);
            String lastName = paramEngine.getParamValue(getServletContext().getInitParameter(LASTNAME_CONFIG_NAME), req, resp);
            int age = Integer.parseInt(paramEngine.getParamValue(getServletContext().getInitParameter(AGE_CONFIG_NAME), req, resp));

            Person person = new Person();

            person.setAge(age);
            person.setFirstname(firstName);
            person.setLastname(lastName);

            writer.write("<p><span style='color: blue'>Hello, " + person + "!</span></p>");
        } catch (IllegalArgumentException e){
            writer.write("<p><span style='color: red'>" + e.getMessage() + "</span></p>");
        }
    }
}
