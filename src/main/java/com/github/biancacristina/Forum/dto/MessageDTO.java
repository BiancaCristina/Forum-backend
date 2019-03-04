package com.github.biancacristina.Forum.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class MessageDTO {

    private Integer id;
    private String text;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime date;

    public MessageDTO() {}

    public MessageDTO(Integer id, String text, LocalDateTime date) {
        this.id = id;
        this.text = text;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
