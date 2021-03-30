package by.it_academy.jd2.messenger.service.api;

import by.it_academy.jd2.messenger.model.dto.User;

import java.util.Map;

/**
 * Created by Vitali Tsvirko
 */
public interface IUserService {
    /**
     * Данный метод предназначен для получения всех пользователей
     * <p>Данные возвращаются в {@code Map}, ключем является имя пользователя </p>
     * @return {@code Map}  всех пользователей
     */
    Map<String, User> getAllUser();

    /**
     * Данные метод возвращает пользователя по его имени
     * @param name имя пользователя
     * @return пользователя с запрашиваемым именем
     */
    User getByName(String name);

    void signUp(User user) throws IllegalArgumentException;

    User authentication(String login, String password);
}
