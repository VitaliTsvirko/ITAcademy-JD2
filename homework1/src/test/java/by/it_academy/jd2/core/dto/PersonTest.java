package by.it_academy.jd2.core.dto;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private final String firstName = "FirstName";
    private final String lastName = "FirstName";
    private final int age = 1;
    private final Person person = new Person(firstName, lastName, age);

    @Test
    void getFirstname() {
        assertEquals(person.getFirstname(), firstName);
    }

    @Test
    void getLastname() {
        assertEquals(person.getLastname(), lastName);
    }

    @Test
    void getAge() {
        assertEquals(person.getAge(), age);
    }

}