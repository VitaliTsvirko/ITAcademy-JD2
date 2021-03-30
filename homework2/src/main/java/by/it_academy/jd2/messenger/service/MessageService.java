package by.it_academy.jd2.messenger.service;

import by.it_academy.jd2.messenger.model.dto.Message;
import by.it_academy.jd2.messenger.model.storage.MessageMemoryStorage;
import by.it_academy.jd2.messenger.model.storage.api.IMessages;
import by.it_academy.jd2.messenger.service.api.IMessageService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Vitali Tsvirko
 */
public class MessageService implements IMessageService {
    private static MessageService instance;
    private final IMessages messageStorage;

    private MessageService(){
        this.messageStorage = new MessageMemoryStorage();
    }

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

    @Override
    public List<Message> getAll() {
        return this.messageStorage.getAll();
    }

    @Override
    public synchronized void add(Message message) {
        if (message.getMessage().isEmpty()){
            throw new IllegalArgumentException("Сообщение не может быть пустым");
        }

        message.setMessageTimeStamp(LocalDateTime.now());

        this.messageStorage.add(message);
    }
}
