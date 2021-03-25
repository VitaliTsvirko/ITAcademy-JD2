package by.it_academy.jd2.messenger.model.storage;

import by.it_academy.jd2.messenger.model.dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class UserMemoryStorageTest {
    private final UserMemoryStorage storage = new UserMemoryStorage();
    private final User user = new User("user", "password", "firstname", "lastname", LocalDate.of(1970,1,1));

    @Test()
    void add() {
        Assertions.assertDoesNotThrow(() -> storage.add(user));
        Assertions.assertThrows(IllegalArgumentException.class, () -> storage.add(user));
        Assertions.assertEquals(storage.getByName(user.getName()), user);
    }
}