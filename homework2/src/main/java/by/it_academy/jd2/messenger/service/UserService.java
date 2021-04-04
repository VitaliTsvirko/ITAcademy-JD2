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
 * Данный класс переназначен работы с пользователями
 * <p>Класс выполняет аутентификацию, авторизацию и регистрацию пользователя,
 * а так же сохраняет данные о пользователи в хранилище</p>
 *
 * @author Vitali Tsvirko
 *
 * @see User
 */
public class UserService implements IUserService {
    private static volatile UserService instance;
    private final IUsers userStorage;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private UserService(){
        this.userStorage = new UserMemoryStorage();
    }

    /**
     * Данный метод метод предназначен для создания и возвращения объекта
     * <p>Если объект еще не создан, то создаться новый объект {@code UserService} и возвращается</p>
     * <p>Если объект был создан ранее, то он и возвращается</p>
     * @return возвращает объект {@code UserService}
     */
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

    /**
     * Данный метод предназначен для получения всех пользователей
     * <p>Данные возвращаются в {@code Map}, ключем является имя пользователя </p>
     * @return {@code Map}  всех пользователей
     */
    @Override
    public Map<String, User> getAllUser() {
        return this.userStorage.getAllUser();
    }

    /**
     * Данные метод возвращает пользователя по его имени
     * @param name имя пользователя
     * @return пользователя с запрашиваемым именем
     *          Если пользователя нет, то вернет {@code null}
     */
    @Override
    public User getByName(String name) {
        return this.userStorage.getByName(name);
    }

    /**
     * Данный метод предназначен для регистрации нового пользователя
     * <p>Обязательные поля пользователя:</p>
     * <ul>
     *  <li>name - имя пользователя</li>
     *  <li>password - пароль</li>
     *  <li>firstname - Имя</li>
     *  <li>lastname - Фамилия</li>
     * </ul>
     * <p>Необаятельное поле</p>
     * <ul>
     *  <li>dateOfBirth - Дата рождения</li>
     *  </ul>
     * @param user пользователь которого необходимо добавить
     * @throws IllegalArgumentException вернет если не заполнено одно из обязательных полей
     */
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

        this.addUser(user);
    }


    /**
     * Данный метод предназначен для аутентификации пользователя
     * @param name введенное имя пользователя
     * @param password введенный пользователем пароль
     * @return пользователь {@code User} прошедший авторизацию
     *         Если введенные имя или пароль не верны но вернет {@code null}
     */
    @Override
    public User authentication(String name, String password) {
        User user = this.getByName(name);

        if (user == null || !user.getPassword().equals(password)){
            return null;
        }

        return user;
    }


    /**
     * Данный метод предназначен для добавления нового пользователя в хранилище
     * @param user пользователь которого необходимо добавить
     * @throws IllegalArgumentException если такой пользователь уже существует
     */
    private synchronized void addUser(User user) throws IllegalArgumentException{
        if (userStorage.getByName(user.getName()) == null){
            this.userStorage.add(user);
        } else {
            throw new IllegalArgumentException("Пользователь " + user.getName() + " уже зарегистрирован!");
        }
    }
}
