package by.it_academy.jd2.messenger.model.storage;

import by.it_academy.jd2.messenger.model.storage.api.IUsers;
import by.it_academy.jd2.messenger.model.dto.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vitali Tsvirko
 */
public class UserMemoryStorage implements IUsers {
    private final Map<String, User> data = new HashMap<>();

    @Override
    public Map<String, User> getAllUser() {
        return this.data;
    }

    @Override
    public User getByName(String name) {
        return this.data.get(name);
    }

    @Override
    public void add(User user) {
        if (this.data.get(user.getName()) == null){
            this.data.put(user.getName(), user);
        } else {
            throw new IllegalArgumentException("Пользователь " + user.getName() + " уже зарегистрирован!");
        }
    }
}
