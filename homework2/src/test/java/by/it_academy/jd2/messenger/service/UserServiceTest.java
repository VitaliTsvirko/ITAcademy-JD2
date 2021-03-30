package by.it_academy.jd2.messenger.service;

import by.it_academy.jd2.messenger.model.dto.User;
import by.it_academy.jd2.messenger.service.api.IUserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private final IUserService userService = UserService.getInstance();

    @Test
    void emptyUserFiled() {
        User user = new User();
        user.setName("admin");

        Assert.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));

        user.setPassword("admin");
        Assert.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));

        user.setFirstName("Vitali");
        Assert.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));

        user.setLastName("Tsvirko");
        Assertions.assertDoesNotThrow(() -> userService.signUp(user));
    }

    @Test
    void setDateOfBirth() {
        User user = new User();
        user.setName("admin");
        user.setPassword("admin");
        user.setFirstName("Vitali");
        user.setFirstName("Tsvirko");
        user.setStringDateOfBirth("2021-03-31");

        Assert.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));
    }

    @Test
    void notUniqUserAdd(){
        User user = new User("user",
                            "password",
                            "firstname",
                            "lastname",
                                    LocalDate.of(1970,1,1));

        Assertions.assertDoesNotThrow(() -> userService.signUp(user));
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));
        Assertions.assertEquals(userService.getByName(user.getName()), user);
    }

}