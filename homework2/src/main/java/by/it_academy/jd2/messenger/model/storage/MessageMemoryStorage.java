package by.it_academy.jd2.messenger.model.storage;

import by.it_academy.jd2.messenger.model.storage.api.IMessages;
import by.it_academy.jd2.messenger.model.dto.Message;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Vitali Tsvirko
 */
public class MessageMemoryStorage implements IMessages {
    private final List<Message> data = new LinkedList<>();

    @Override
    public List<Message> getAll() {
        return this.data;
    }

    @Override
    public void add(Message message) {
        this.data.add(message);
    }
}
