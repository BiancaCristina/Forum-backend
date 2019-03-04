package com.github.biancacristina.Forum.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void shouldNotHaveEqualNickname() {
        User u1 = new User(null,"José", "jj12", "jose@gmail.com", "123");
        User u2 = new User(null,"Maria", "mm12", "maria@gmail.com", "456");

        assertNotEquals(u1.getNickname(), u2.getNickname());
    }

    @Test
    public void shouldNotHaveEqualEmails() {
        User u1 = new User(null,"José", "jj12", "jose@gmail.com", "123");
        User u2 = new User(null,"Maria", "mm12", "maria@gmail.com", "456");

        assertNotEquals(u1.getEmail(), u2.getEmail());
    }

    @Test
    public void shouldNotHaveEqualIds() {
        User u1 = new User(null,"José", "jj12", "jose@gmail.com", "123");
        User u2 = new User(null,"Maria", "mm12", "maria@gmail.com", "456");

        assertNotEquals(u1.getId(), u2.getId());
    }

}
