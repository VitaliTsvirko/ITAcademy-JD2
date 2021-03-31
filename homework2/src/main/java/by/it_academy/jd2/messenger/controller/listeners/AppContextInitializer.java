package by.it_academy.jd2.messenger.controller.listeners;

import by.it_academy.jd2.messenger.model.storage.api.IMessages;
import by.it_academy.jd2.messenger.model.storage.api.IUsers;
import by.it_academy.jd2.messenger.model.dto.Message;
import by.it_academy.jd2.messenger.model.dto.User;
import by.it_academy.jd2.messenger.service.MessageService;
import by.it_academy.jd2.messenger.service.UserService;
import by.it_academy.jd2.messenger.service.api.IMessageService;
import by.it_academy.jd2.messenger.service.api.IUserService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

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

        IUserService userService = UserService.getInstance();
        IMessageService messageService = MessageService.getInstance();

      //Добавляем пользователя по-умолчанию
        User user = new User();
        user.setName("admin");
        user.setPassword("admin");
        user.setFirstName("Vitali");
        user.setLastName("Tsvirko");
        userService.signUp(user);

        //Добавляем сообщение
        Message initMessage = new Message();
        initMessage.setUser(user.getName());
        initMessage.setMessage("Initial message from admin");

        messageService.add(initMessage);

        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("messageService", messageService);
    }
}
