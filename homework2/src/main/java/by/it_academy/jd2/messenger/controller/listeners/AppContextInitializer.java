package by.it_academy.jd2.messenger.controller.listeners;

import by.it_academy.jd2.messenger.model.storage.api.IMessages;
import by.it_academy.jd2.messenger.model.storage.api.IUsers;
import by.it_academy.jd2.messenger.model.storage.MessageMemoryStorage;
import by.it_academy.jd2.messenger.model.storage.UserMemoryStorage;
import by.it_academy.jd2.messenger.model.dto.Message;
import by.it_academy.jd2.messenger.model.dto.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Данный класс предназначен для инициализации переменных приложения.
 * <p>Переменные сохраняются в servletContext</p>
 * <p>usersContainer - хранилище пользователей</p>
 * <p>messageContainer - хранилище сообщений</p>
 *
 * @author Vitali Tsvirko
 *
 * @see IMessages
 * @see IUsers
 * @see Message
 * @see User
 */
public class AppContextInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        IMessages messageContainer = new MessageMemoryStorage();
        IUsers usersContainer = new UserMemoryStorage();

        //Добавляем пользователя по-умолчанию
        User user = new User("admin", "admin", "Vitali", "Tsvirko", LocalDate.of(1970, 1, 1));

        usersContainer.add(user);
        messageContainer.add(new Message(user.getName(), "Initial message from admin", LocalDateTime.now()));

        servletContext.setAttribute("usersContainer", usersContainer);
        servletContext.setAttribute("messageContainer", messageContainer);
    }
}
