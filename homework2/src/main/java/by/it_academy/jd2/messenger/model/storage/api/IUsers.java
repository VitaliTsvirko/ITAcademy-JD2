package by.it_academy.jd2.messenger.model.storage.api;

import by.it_academy.jd2.messenger.model.dto.User;

import java.util.Map;

/**
 * @author Vitali Tsvirko
 */
public interface IUsers {
    Map<String, User> getAllUser();
    User getByName(String name);
    void add(User user);
}
