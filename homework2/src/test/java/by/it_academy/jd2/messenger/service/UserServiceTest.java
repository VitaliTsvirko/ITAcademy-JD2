package by.it_academy.jd2.messenger.service;

import by.it_academy.jd2.messenger.model.dto.User;
import by.it_academy.jd2.messenger.model.storage.UserMemoryStorage;
import by.it_academy.jd2.messenger.service.api.IUserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;


class UserServiceTest {
    private IUserService userService = UserService.getInstance();
    private User user;

    @BeforeEach
    public void userSetUp(){
        //Создаем новый экземпляр пустого хранилища
        try{
            Field userStorage = UserService.class.getDeclaredField("userStorage");
            userStorage.setAccessible(true);
            userStorage.set(userService, new UserMemoryStorage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.user = new User();
    }


    @Test
    void TestSignUp_EmptyUserFiled() {
        Assert.assertThrows(IllegalArgumentException.class, () -> userService.signUp(this.user));

        this.user.setName("user");
        Assert.assertThrows(IllegalArgumentException.class, () -> userService.signUp(this.user));

        this.user.setPassword("user");
        Assert.assertThrows(IllegalArgumentException.class, () -> userService.signUp(this.user));

        this.user.setFirstName("FirstName");
        Assert.assertThrows(IllegalArgumentException.class, () -> userService.signUp(this.user));

        this.user.setLastName("LastName");
        Assertions.assertDoesNotThrow(() -> userService.signUp(this.user));
    }

    @Test
    void TestSignUp_UserNameValidation(){
        this.user = new User("user",
                "password",
                "FirstName",
                "LastName");

        this.user.setName("привет");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));

        this.user.setName("us er");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));

        this.user.setName("user ");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));

        this.user.setName(" user");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));

        this.user.setName(" ");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));

        this.user.setName("user!");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));

        this.user.setName("#,.$?<>()");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));

        this.user.setName("user_user");
        Assertions.assertDoesNotThrow(() -> userService.signUp(user));

        this.user.setName("user-user");
        Assertions.assertDoesNotThrow(() -> userService.signUp(user));
    }

    @Test
    void TestSignUp_PasswordValidation(){
        this.user = new User("user",
                "password",
                "FirstName",
                "LastName");


        /*user.setPassword("#!!!");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));*/

        this.user.setPassword("a a");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));

        this.user.setPassword(" a");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));

        this.user.setPassword("a  ");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));

        this.user.setPassword(" ");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));
    }


    @Test
    void TestSignUp_WrongDateOfBirth() {
        this.user.setName("user");
        this.user.setPassword("user");
        this.user.setFirstName("FirstName");
        this.user.setLastName("LastName");

        this.user.setStringDateOfBirth("02-30");
        Assert.assertThrows(IllegalArgumentException.class, () -> userService.signUp(this.user));

        this.user.setStringDateOfBirth("2021-30");
        Assert.assertThrows(IllegalArgumentException.class, () -> userService.signUp(this.user));
    }

    @Test
    void TestSignUp_CorrectDateOfBirth() {
        this.user.setName("user");
        this.user.setPassword("user");
        this.user.setFirstName("FirstName");
        this.user.setLastName("LastName");

        this.user.setStringDateOfBirth("2021-03-30");
        Assertions.assertDoesNotThrow(() -> userService.signUp(this.user));

        this.user.setName("user2");
        this.user.setStringDateOfBirth("");
        Assertions.assertDoesNotThrow(() -> userService.signUp(this.user));
    }


    @Test
    void TestSignUp_NotUniqUserAdd(){
        this.user = new User("user",
                            "password",
                            "FirstName",
                            "LastName");

        Assertions.assertDoesNotThrow(() -> userService.signUp(user));

        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.signUp(user));
        Assertions.assertEquals(userService.getByName(user.getName()), user);
    }


    @Test
    void TestAuthentication_EmptyLoginOrPassword() {
        User authUser = userService.authentication(null, null);
        Assertions.assertNull(authUser);

        authUser = userService.authentication("", "");
        Assertions.assertNull(authUser);
    }

    @Test
    void TestAuthentication_IncorrectLoginOrPassword() {
        this.user = new User("user",
                "password",
                            "firstname",
                            "lastname");
        userService.signUp(this.user);

        Assertions.assertNull(userService.authentication("user", "password1"));

        Assertions.assertNull(userService.authentication("user1", "password"));
    }



    @Test
    void TestAuthentication_CorrectLoginOrPassword() {
        String userName = "user";
        String userPassword = "password";

        this.user = new User(userName,
                            userPassword,
                            "firstname",
                            "lastname");
        userService.signUp(this.user);

        Assertions.assertEquals(this.user, userService.authentication(userName, userPassword));
    }


    @Test
    void TestGetAllUser() {
        Assertions.assertEquals(userService.getAllUser().size(), 0);
    }

}