package com.github.biancacristina.Forum.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.biancacristina.Forum.domain.enums.Profile;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique=true)
    private String nickname;

    @Column(unique=true)
    private String email;

    @JsonIgnore
    private String password;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="PROFILES")
    private Set<Integer> profiles = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade= CascadeType.ALL)
    private List<Topic> topics = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy= "user", cascade= CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    public User() {}

    public User(String name, String nickname, String email, String password) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;

        // Every user is a normal user
        setProfiles(Profile.USER);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profile> getProfiles() {
        // Convert a list of Integer into a Profile list
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void setProfiles(Profile profile) {
        // Store only the code of a profile
        profiles.add(profile.getCod());
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void addTopic(Topic topic) {
        this.topics.add(topic);
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

