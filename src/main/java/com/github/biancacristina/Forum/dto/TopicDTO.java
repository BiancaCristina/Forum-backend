package com.github.biancacristina.Forum.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class TopicDTO {

    private Integer id;
    private String title;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime date;

    public TopicDTO() {}

    public TopicDTO(Integer id, String title, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
