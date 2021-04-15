package by.it_academy.jd2.messenger.model.storage.memory;

import by.it_academy.jd2.messenger.model.storage.api.IMessages;
import by.it_academy.jd2.messenger.model.dto.Message;

import java.util.LinkedList;
import java.util.List;

/**
 * Данный класс переназначен для хранения сообщений в памяти
 *
 * @author Vitali Tsvirko
 *
 * @see Message
 */
public class MessageMemoryStorage implements IMessages {
    private final List<Message> data = new LinkedList<>();

    /**
     * Данный метод переназначен для получения всех сообщений
     * @return {@code List} объектов {@code Message} сообщений
     */
    @Override
    public List<Message> getAll() {
        return this.data;
    }

    /**
     * Данный метод добавляет полученной сообщение в хранилище
     * @param message сообщение которое необходимо добавить в хранилище
     */
    @Override
    public void add(Message message) {
        this.data.add(message);
    }
}
