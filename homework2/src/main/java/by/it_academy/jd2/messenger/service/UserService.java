package by.it_academy.jd2.messenger.service;

import by.it_academy.jd2.messenger.model.dto.User;
import by.it_academy.jd2.messenger.model.storage.UserMemoryStorage;
import by.it_academy.jd2.messenger.model.storage.api.IUsers;
import by.it_academy.jd2.messenger.service.api.IUserService;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

/**
 * Created by Vitali Tsvirko
 */
public class UserService implements IUserService {
    private static UserService instance;
    private final IUsers userStorage;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private UserService(){
        this.userStorage = new UserMemoryStorage();
    }

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class){
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }
        return instance;
    }

    @Override
    public Map<String, User> getAllUser() {
        return this.userStorage.getAllUser();
    }

    @Override
    public User getByName(String name) {
        return this.userStorage.getByName(name);
    }

    @Override
    public void signUp(User user) throws IllegalArgumentException{
        if (StringUtils.isEmpty(user.getName())){
            throw new IllegalArgumentException("Не задано имя пользователя");
        }

        if (StringUtils.isEmpty(user.getPassword())){
            throw new IllegalArgumentException("Не задан пароль");
        }

        if (StringUtils.isEmpty(user.getFirstName())){
            throw new IllegalArgumentException("Не задано имя");
        }

        if (StringUtils.isEmpty(user.getLastName())){
            throw new IllegalArgumentException("Не задано фамилия");
        }

        if (!StringUtils.isEmpty(user.getStringDateOfBirth())) {
            try {
                user.setDateOfBirth(LocalDate.parse(user.getStringDateOfBirth(), this.dateTimeFormatter));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Ошибка ввода даты. Проверьте данные и повторите ввод.");
            }
        }

        System.out.println(user.toString());


        this.addUser(user);
    }


    @Override
    public User authentication(String name, String password) {
        User user = this.getByName(name);

        if (user == null || !user.getPassword().equals(password)){
            return null;
        }

        return user;
    }


    private synchronized void addUser(User user) {
        if (userStorage.getByName(user.getName()) == null){
            this.userStorage.add(user);
        } else {
            throw new IllegalArgumentException("Пользователь " + user.getName() + " уже зарегистрирован!");
        }
    }
}
