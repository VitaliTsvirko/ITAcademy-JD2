package by.it_academy.jd2.messenger.service;

import by.it_academy.jd2.messenger.model.dto.Message;
import by.it_academy.jd2.messenger.model.storage.MessageMemoryStorage;
import by.it_academy.jd2.messenger.service.api.IMessageService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

class MessageServiceTest {
    private IMessageService messageService = MessageService.getInstance();
    private Message message;

    @BeforeEach
    public void userSetUp(){
        //Создаем новый экземпляр пустого хранилища
        try{
            Field messageStorage = MessageService.class.getDeclaredField("messageStorage");
            messageStorage.setAccessible(true);
            messageStorage.set(messageService, new MessageMemoryStorage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.message = new Message();
    }

    @Test
    void TestAddMessage_EmptyMessageField() {
        this.message.setMessage("");
        Assert.assertThrows(IllegalArgumentException.class, () -> messageService.add(message));

        this.message.setMessage("message");
        this.message.setUser("");
        Assert.assertThrows(IllegalArgumentException.class, () -> messageService.add(message));
    }

    @Test
    void TestAddMessage_CorrectMessage() {
        this.message.setMessage("message");
        this.message.setUser("user");
        Assertions.assertDoesNotThrow(() -> messageService.add(message));
    }


    @Test
    void TestGetAllMessages() {
        Assertions.assertEquals(messageService.getAll().size(), 0);
    }
}