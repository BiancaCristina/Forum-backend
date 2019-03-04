package com.github.biancacristina.Forum.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewUserDTO {

    private Integer id;

    @NotEmpty
    @NotNull
    @Length(min=5, max= 60, message= "Deve ter entre 5 e 60 caracteres.")
    private String name;

    @NotEmpty
    @NotNull
    @Length(min=4, max= 20, message= "Deve ter entre 4 e 20 caracteres.")
    private String nickname;

    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    @Length(min=8, max= 16, message= "Deve ter entre 8 e 16 caracteres.")
    private String password;

    public NewUserDTO() {}

    public NewUserDTO(Integer id, String name, String nickname, String email, String password) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
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
}
