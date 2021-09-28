package com.delivery.database.entities;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private User user;

    @Before
    public void initUser() {
        user = new User();
    }

    @Test
    public void testConstUser() {
        assertEquals("user", User.ROLE_USER);
    }

    @Test
    public void testConstManager() {
        assertEquals("manager", User.ROLE_MANAGER);
    }

    @Test
    public void testSetAndGetName() {
        String name = "Name";
        user.setName(name);
        assertEquals(name, user.getName());
    }

    @Test
    public void testSetAndGetSurname() {
        String surname = "Surname";
        user.setSurname(surname);
        assertEquals(surname, user.getSurname());
    }

    @Test
    public void testSetAndGetEmail() {
        String email = "Email";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testSetAndGetLogin() {
        String login = "Login";
        user.setLogin(login);
        assertEquals(login, user.getLogin());
    }

}
