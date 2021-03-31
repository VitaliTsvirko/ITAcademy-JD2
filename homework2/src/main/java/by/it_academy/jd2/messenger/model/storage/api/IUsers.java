package by.it_academy.jd2.messenger.model.storage.api;

import by.it_academy.jd2.messenger.model.dto.User;

import java.util.Map;

/**
 * Интерфейс для работы с хранилищем пользователей
 *
 * @author Vitali Tsvirko
 *
 * @see User
 */
public interface IUsers {
    /**
     * Данный метод предназначен для получения всей пользователей
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

    /**
     * Данный метод добавляет пользователя в хранилище
     * @param user пользователь которого необходимо добавить
     *             Если пользователь уже присутствует то возвращается исключение {@code IllegalArgumentException}
     */
    void add(User user);
}
