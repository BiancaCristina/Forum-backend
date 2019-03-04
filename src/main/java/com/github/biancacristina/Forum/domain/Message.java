package com.github.biancacristina.Forum.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Message {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String text;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime creationDate;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime lastEdited;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name= "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    public Message() {}

    public Message(String text, Topic topic, User user) {
        this.text = text;
        this.topic = topic;
        this.user = user;
        this.creationDate = LocalDateTime.now();
        this.lastEdited = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return id.equals(message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
