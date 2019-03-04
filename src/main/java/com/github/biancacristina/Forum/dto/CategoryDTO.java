package com.github.biancacristina.Forum.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryDTO {

    private Integer id;

    @NotEmpty
    @NotNull
    @Length(min=5, max= 50, message= "Deve ter entre 5 e 50 caracteres.")
    private String name;

    public CategoryDTO() {}

    public CategoryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
