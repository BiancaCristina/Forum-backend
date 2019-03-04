package com.github.biancacristina.Forum.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {

    private Integer id;

    @NotEmpty
    @NotNull
    private String nickname;

    public UserDTO() {}

    public UserDTO(Integer id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
