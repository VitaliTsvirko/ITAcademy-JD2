package by.it_academy.jd2.messenger.service;

import by.it_academy.jd2.messenger.model.dto.Message;
import by.it_academy.jd2.messenger.model.storage.MessageMemoryStorage;
import by.it_academy.jd2.messenger.model.storage.api.IMessages;
import by.it_academy.jd2.messenger.service.api.IMessageService;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Данный класс переназначен работы с сообщениями
 * <p>Класс выполняет сохранение сообщений в хранилище,
 * а так же получение сообщений из хранилища</p>
 *
 * @author Vitali Tsvirko
 *
 * @see Message
 */
public class MessageService implements IMessageService {
    private static MessageService instance;
    private final IMessages messageStorage;

    private MessageService(){
        this.messageStorage = new MessageMemoryStorage();
    }

    /**
     * Данный метод метод предназначен для создания и возвращения объекта
     * <p>Если объект еще не создан, то создаться новый объект {@code MessageService} и возвращается</p>
     * <p>Если объект был создан ранее, то он и возвращается</p>
     * @return возвращает объект {@code MessageService}
     */
    public static MessageService getInstance() {
        if (instance == null) {
            synchronized (MessageService.class){
                if (instance == null) {
                    instance = new MessageService();
                }
            }
        }
        return instance;
    }

    /**
     * Данный метод предназначен для получения всех сообщений
     * @return {@code List} всех сообщений
     */
    @Override
    public List<Message> getAll() {
        return this.messageStorage.getAll();
    }


    /**
     * Данный метод предназначен для добавления сообщений в хранилище
     * @param message сообщение которое необходимо добавить
     * @throws IllegalArgumentException если передано пустое сообщение
     */
    @Override
    public void add(Message message) {
        if (StringUtils.isEmpty(message.getMessage())){
            throw new IllegalArgumentException("Сообщение не может быть пустым");
        }

        if (StringUtils.isEmpty(message.getUser())){
            throw new IllegalArgumentException("Передано сообщение без указания пользователя");
        }

        message.setMessageTimeStamp(LocalDateTime.now());

        synchronized (messageStorage){
            this.messageStorage.add(message);
        }
    }
}
