package by.it_academy.jd2.messenger.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Данный класс предназначен для хранения сообщения чата
 *
 * @author Vitali Tsvirko
 */
public class Message implements Serializable {
    private String user;
    private String message;
    private LocalDateTime messageTimeStamp;

    public Message(String user, String message, LocalDateTime messageTimeStamp) {
        this.user = user;
        this.message = message;
        this.messageTimeStamp = messageTimeStamp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getMessageTimeStamp() {
        return messageTimeStamp;
    }

    public void setMessageTimeStamp(LocalDateTime messageTimeStamp) {
        this.messageTimeStamp = messageTimeStamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "user='" + user + '\'' +
                ", message='" + message + '\'' +
                ", messageTimeStamp=" + messageTimeStamp +
                '}';
    }
}
