package by.it_academy.jd2.messenger.model.storage.api;

import by.it_academy.jd2.messenger.model.dto.Message;

import java.util.List;

/**
 * Интерфейс для работы с хранилищем сообщений
 *
 * @author Vitali Tsvirko
 *
 * @see Message
 */
public interface IMessages {
    /**
     * Данный метод переназначен для получения всех сообщений
     * @return {@code List} объектов {@code Message} сообщений
     */
    List<Message> getAll();

    /**
     * Данный метод добавляет полученной сообщение в хранилище
     * @param message сообщение которое необходимо добавить в хранилище
     */
    void add(Message message);

}
