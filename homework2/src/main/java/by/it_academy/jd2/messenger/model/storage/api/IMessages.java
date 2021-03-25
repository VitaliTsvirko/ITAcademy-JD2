package by.it_academy.jd2.messenger.model.storage.api;

import by.it_academy.jd2.messenger.model.dto.Message;

import java.util.List;

/**
 * @author Vitali Tsvirko
 */
public interface IMessages {
    List<Message> getAll();
    void add(Message message);

}
