package by.it_academy.jd2.messenger.model.storage.database;

import by.it_academy.jd2.messenger.model.dto.User;
import by.it_academy.jd2.messenger.model.storage.api.IUsers;
import by.it_academy.jd2.messenger.model.storage.database.core.ConnectionPoolCreator;
import org.postgresql.util.PSQLException;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Данный класс предназначен для хранения пользователей в памяти
 *
 * @author Vitali Tsvirko
 *
 * @see User
 */
public class UserDBStorage implements IUsers {
    private final DataSource dataSource = ConnectionPoolCreator.getInstance();

    public UserDBStorage() throws PropertyVetoException {
    }

    /**
     * Данный метод предназначен для получения всей пользователей
     * <p>Данные возвращаются в {@code Map}, ключем является имя пользователя </p>
     * @return {@code Map}  всех пользователей
     */
    @Override
    public Map<String, User> getAllUser() throws IllegalAccessException {
        Map<String, User> result = new HashMap<>();

        String sql = "Select * from messenger.users";

        try (Connection connection = dataSource.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()){
                        User user = new User();
                        user.setName(rs.getString("login"));
                        user.setPassword(rs.getString("pwd"));
                        user.setFirstName(rs.getString("first_name"));
                        user.setLastName(rs.getString("last_name"));
                        user.setDateOfBirth(rs.getObject("date_of_birth", LocalDate.class));

                        result.put(user.getName(), user);
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalAccessException("Ошибка работы с базой данных");
        }

        return result;
    }

    /**
     * Данные метод возвращает пользователя по его имени
     * @param name имя пользователя
     * @return пользователя с запрашиваемым именем
     *          Если пользователя нет, то вернет {@code null}
     */
    @Override
    public User getByName(String name) throws IllegalAccessException {
        User result = null;

        String sql = "Select * from messenger.users WHERE login=? ";

        try (Connection connection = dataSource.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, name);

                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()){
                        result = new User();
                        result.setName(rs.getString("login"));
                        result.setPassword(rs.getString("pwd"));
                        result.setFirstName(rs.getString("first_name"));
                        result.setLastName(rs.getString("last_name"));
                        result.setDateOfBirth(rs.getObject("date_of_birth", LocalDate.class));
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalAccessException("Ошибка работы с базой данных");
        }

        return result;
    }

    /**
     * Данный метод добавляет пользователя в хранилище
     * @param user пользователь которого необходимо добавить
     *             Если пользователь уже присутствует то возвращается исключение {@code IllegalArgumentException}
     */
    @Override
    public void add(User user) throws IllegalAccessException, IllegalArgumentException {
        String sql = "INSERT INTO messenger.users (login, pwd, first_name, last_name, date_of_birth)\n" +
                "    VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, user.getName());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFirstName());
                ps.setString(4, user.getLastName());
                if (user.getDateOfBirth() != null) {
                    ps.setObject(5, user.getDateOfBirth());
                } else {
                    ps.setObject(5, null);
                }

                if (ps.executeUpdate() == 0){
                    throw new IllegalArgumentException("Ошибка добавления сообщения в базу");
                }
            }
        } catch (PSQLException e) {
            if (e.getSQLState().equals("23505")){
                throw new IllegalArgumentException("Пользователь " + user.getName() + " уже зарегистрирован!");
            } else {
                throw new IllegalAccessException("Ошибка работы с базой данных");
            }
        } catch (SQLException e) {
            throw new IllegalAccessException("Ошибка работы с базой данных");
        }

    }


    public static void main(String[] args) throws IllegalAccessException, PropertyVetoException {
        UserDBStorage userDBStorage = new UserDBStorage();
       User user = new User("user13",
                "password",
                "FirstName",
                "LastName");

       user.setDateOfBirth(LocalDate.now());

       userDBStorage.add(user);

        User user2 = userDBStorage.getByName("user2");

        System.out.println(user2.getLastName());
    }
}
