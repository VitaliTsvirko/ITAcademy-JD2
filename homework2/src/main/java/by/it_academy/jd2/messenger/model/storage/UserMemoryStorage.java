package by.it_academy.jd2.messenger.model.storage;

import by.it_academy.jd2.messenger.model.storage.api.IUsers;
import by.it_academy.jd2.messenger.model.dto.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Данный класс предназначен для хранения пользователей в памяти
 *
 * @author Vitali Tsvirko
 *
 * @see User
 */
public class UserMemoryStorage implements IUsers {
    private final Map<String, User> data = new HashMap<>();

    /**
     * Данный метод предназначен для получения всей пользователей
     * <p>Данные возвращаются в {@code Map}, ключем является имя пользователя </p>
     * @return {@code Map}  всех пользователей
     */
    @Override
    public Map<String, User> getAllUser() {
        return this.data;
    }

    /**
     * Данные метод возвращает пользователя по его имени
     * @param name имя пользователя
     * @return пользователя с запрашиваемым именем
     *          Если пользователя нет, то вернет {@code null}
     */
    @Override
    public User getByName(String name) {
        return this.data.get(name);
    }

    /**
     * Данный метод добавляет пользователя в хранилище
     * @param user пользователь которого необходимо добавить
     *             Если пользователь уже присутствует то возвращается исключение {@code IllegalArgumentException}
     */
    @Override
    public void add(User user) {
        if (this.data.get(user.getName()) == null){
            this.data.put(user.getName(), user);
        } else {
            throw new IllegalArgumentException("Пользователь " + user.getName() + " уже зарегистрирован!");
        }
    }
}
