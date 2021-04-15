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
     * @throws IllegalAccessException при ошибках подключения к хранилищу
     */
    Map<String, User> getAllUser() throws IllegalAccessException;

    /**
     * Данные метод возвращает пользователя по его имени
     * @param name имя пользователя
     * @return пользователя с запрашиваемым именем
     * @throws IllegalAccessException при ошибках подключения к хранилищу
     */
    User getByName(String name) throws IllegalAccessException;

    /**
     * Данный метод добавляет пользователя в хранилище
     * @param user пользователь которого необходимо добавить
     * @throws IllegalAccessException при ошибках подключения к хранилищу
     * @throws IllegalArgumentException если пользователь уже присутствует в хранилище
     */
    void add(User user) throws IllegalArgumentException, IllegalAccessException;
}
