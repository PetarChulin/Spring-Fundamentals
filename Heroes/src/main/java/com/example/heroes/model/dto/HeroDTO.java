package com.example.heroes.model.dto;

import com.example.heroes.model.entity.ClassEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class HeroDTO {

    @NotEmpty(message = "You must enter name!")
    private String name;
    @NotNull(message = "You must pick a type!")
    private ClassEnum type;
    @NotNull(message = "You must enter level!")
    @Positive(message = "Level must be positive!")
    private Integer level;

    public HeroDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassEnum getType() {
        return type;
    }

    public void setType(ClassEnum type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
