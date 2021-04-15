package by.it_academy.jd2.messenger.service.api;

import by.it_academy.jd2.messenger.model.dto.User;

import java.util.Map;

/**
 * @author Vitali Tsvirko
 */
public interface IUserService {
    /**
     * Данный метод предназначен для получения всех пользователей
     * <p>Данные возвращаются в {@code Map}, ключем является имя пользователя </p>
     * @return {@code Map}  всех пользователей
     */
    Map<String, User> getAllUser() throws IllegalAccessException;

    /**
     * Данные метод возвращает пользователя по его имени
     * @param name имя пользователя
     * @return пользователя с запрашиваемым именем
     */
    User getByName(String name) throws IllegalAccessException;

    void signUp(User user) throws IllegalArgumentException, IllegalAccessException;

    User authentication(String login, String password) throws IllegalAccessException;
}
