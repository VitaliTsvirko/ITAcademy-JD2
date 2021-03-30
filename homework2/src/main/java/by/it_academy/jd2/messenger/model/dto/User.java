package by.it_academy.jd2.messenger.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Данный класс предназначен для хранения данных пользователя мессенджера
 *
 * @author Vitali Tsvirko
 */
public class User implements Serializable {
    private String name;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String stringDateOfBirth;


    public User (){

    }

    public User(String name, String password, String firstName, String lastName, LocalDate dateOfBirth) {
        this.name = name;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStringDateOfBirth() {
        return stringDateOfBirth;
    }

    public void setStringDateOfBirth(String stringDateOfBirth) {
        this.stringDateOfBirth = stringDateOfBirth;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) && password.equals(user.password) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && dateOfBirth.equals(user.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, firstName, lastName, dateOfBirth);
    }
}
