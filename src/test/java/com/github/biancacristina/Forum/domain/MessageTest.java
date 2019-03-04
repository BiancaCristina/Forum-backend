package com.github.biancacristina.Forum.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void shouldNotHaveNullDates() {
        Category category = new Category(null, "Category Test");
        User user = new User(null,"José", "jj12", "jose@gmail.com", "123");
        Topic topic = new Topic(null,"Tópico 1", "Mensagem inicial tópico 1", category, user);
        Message message = new Message("Mensagem teste", topic, user);

        assertNotNull(message.getCreationDate());
        assertNotNull(message.getLastEdited());
    }

    @Test
    public void shouldNotHaveEqualIds() {
        Category category = new Category(null, "Category Test");
        User user = new User(null,"José", "jj12", "jose@gmail.com", "123");
        Topic topic = new Topic(null,"Tópico 1", "Mensagem inicial tópico 1", category, user);
        Message m1 = new Message("Mensagem teste 1", topic, user);
        Message m2 = new Message("Mensagem teste 2", topic, user);

        assertNotEquals(m1.getId(), m2.getId());
    }
}
