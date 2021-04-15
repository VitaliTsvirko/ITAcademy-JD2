package by.it_academy.jd2.messenger.model.storage.database;

import by.it_academy.jd2.messenger.model.dto.Message;
import by.it_academy.jd2.messenger.model.storage.api.IMessages;
import by.it_academy.jd2.messenger.model.storage.database.core.ConnectionPoolCreator;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Данный класс переназначен для хранения сообщений в памяти
 *
 * @author Vitali Tsvirko
 *
 * @see Message
 */
public class MessageDBStorage implements IMessages {
    private final DataSource dataSource = ConnectionPoolCreator.getInstance();

    public MessageDBStorage() throws PropertyVetoException {
    }

    /**
     * Данный метод переназначен для получения всех сообщений
     * @return {@code List} объектов {@code Message} сообщений
     */
    @Override
    public List<Message> getAll() throws IllegalAccessException {
        List<Message> result = new LinkedList<>();

        String sql = "SELECT * FROM messenger.message ORDER BY id ASC";

        try (Connection connection = dataSource.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()){
                        Message message = new Message();
                        message.setMessage(rs.getString("message"));
                        message.setUser(rs.getString("user_name"));
                        message.setMessageTimeStamp(rs.getObject("message_timestamp", LocalDateTime.class));

                        result.add(message);
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalAccessException("Ошибка работы с базой данных");
        }

        return result;
    }

    /**
     * Данный метод добавляет полученной сообщение в хранилище
     * @param message сообщение которое необходимо добавить в хранилище
     */
    @Override
    public void add(Message message) throws IllegalAccessException {
        String sql = "INSERT INTO messenger.message (user_name, message, message_timestamp)" +
                "    VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection()){
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, message.getUser());
                ps.setString(2, message.getMessage());
                ps.setTimestamp(3, Timestamp.valueOf(message.getMessageTimeStamp()));

                if (ps.executeUpdate() == 0){
                    throw new IllegalArgumentException("Ошибка добавления сообщения в базу");
                }
            }
        } catch (SQLException e) {
            throw new IllegalAccessException("Ошибка работы с базой данных");
        }
    }


    public static void main(String[] args) throws PropertyVetoException, IllegalAccessException {
        MessageDBStorage db = new MessageDBStorage();



        Message message = new Message();

        message.setMessage("dksjflkdsf");
        message.setUser("user2");
        message.setMessageTimeStamp(LocalDateTime.now());

        Timestamp timestamp = Timestamp.valueOf(message.getMessageTimeStamp());

        db.add(message);

        List<Message> all = db.getAll();

        System.out.println(all.size());

    }
}
