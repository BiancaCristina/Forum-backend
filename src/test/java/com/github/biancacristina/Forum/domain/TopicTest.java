package com.github.biancacristina.Forum.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class TopicTest {

    @Test
    public void shouldNotHaveNullUser() {
        Category category = new Category(null, "Category Test");
        User user = new User("José", "jj12", "jose@gmail.com", "123");
        Topic topic = new Topic("Tópico 1", "Mensagem inicial tópico 1", category, user);

        assertNotNull(topic.getUser());
    }

    @Test
    public void shouldNotHaveNullDates() {
        Category category = new Category(null, "Category Test");
        User user = new User("José", "jj12", "jose@gmail.com", "123");
        Topic topic = new Topic("Tópico 1", "Mensagem inicial tópico 1", category, user);

        assertNotNull(topic.getCreationDate());
        assertNotNull(topic.getLastEdited());
    }

    @Test
    public void shouldHaveAtLeastOneMessage() {
        Category category = new Category(null, "Category Test");
        User user = new User("José", "jj12", "jose@gmail.com", "123");
        Topic topic = new Topic("Tópico 1", "Mensagem inicial tópico 1", category, user);

        assertNotEquals(0, topic.getMessages().size());
    }

    @Test
    public void shouldNotHaveEqualIds() {
        Category category = new Category(null, "Category Test");
        User user = new User("José", "jj12", "jose@gmail.com", "123");
        Topic t1 = new Topic("Tópico 1", "Mensagem inicial tópico 1", category, user);
        Topic t2 = new Topic("Tópico 1", "Mensagem inicial tópico 1", category, user);

        assertNotEquals(t1.getId(), t2.getId());
    }
}

