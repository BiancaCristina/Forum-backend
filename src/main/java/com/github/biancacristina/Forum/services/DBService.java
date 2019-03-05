package com.github.biancacristina.Forum.services;

import com.github.biancacristina.Forum.domain.Category;
import com.github.biancacristina.Forum.domain.Message;
import com.github.biancacristina.Forum.domain.Topic;
import com.github.biancacristina.Forum.domain.User;
import com.github.biancacristina.Forum.repositories.CategoryRepository;
import com.github.biancacristina.Forum.repositories.MessageRepository;
import com.github.biancacristina.Forum.repositories.TopicRepository;
import com.github.biancacristina.Forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public void instantiateTestDatabase() throws ParseException {
        User u1 = new User(null,"José", "jj12", "jose@gmail.com", pe.encode("123"));
        User u2 = new User(null,"Maria", "mm12", "maria@gmail.com", pe.encode("456"));

        userRepository.saveAll(Arrays.asList(u1,u2));

        Category cat1 = new Category(null, "Soluções");
        Category cat2 = new Category(null, "Dúvidas");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));

        Topic t1 = new Topic(null, "Tópico 1", "Implementando Hello World!", cat1, u1);
        Topic t2 = new Topic(null, "Tópico 2", "O que é Spring Boot?", cat2, u2);

        u1.addTopic(t1);
        u2.addTopic(t2);

        topicRepository.saveAll(Arrays.asList(t1,t2));
        userRepository.saveAll(Arrays.asList(u1,u2));

        Message m1 = t1.getMessages().get(0);
        Message m2 = t2.getMessages().get(0);

        messageRepository.saveAll(Arrays.asList(m1,m2));
    }
}
