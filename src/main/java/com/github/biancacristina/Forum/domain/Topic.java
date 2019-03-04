package com.github.biancacristina.Forum.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Topic {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime creationDate;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime lastEdited;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name= "category_id")
    private Category category;

    @OneToMany(mappedBy= "topic")
    private List<Message> messages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    public Topic(String title, String text, Category category, User user) {
        this.title = title;
        this.category = category;
        this.user = user;
        this.creationDate = LocalDateTime.now();
        this.lastEdited = LocalDateTime.now();

        // Use the text to initialize the first message of the topic
        addMessage(new Message(text, this, user));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

