package com.example.andreys.model.dto;

import com.example.andreys.model.entity.CategoryEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ItemDTO {

    @Size(min=2 , message = "Incorrect name!")
    @NotNull
    private String name;

    @Size(min = 3, message = "Incorrect description!")
    private String description;

    @Positive(message = "Incorrect price!")
    @NotNull
    private BigDecimal price;

    @NotNull
    private CategoryEnum category;

    @NotNull
    private String gender;

    public ItemDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
