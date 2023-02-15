package com.example.shopping_list.model.dto;


import com.example.shopping_list.model.entity.CategoryEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDTO {

    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 5)
    @NotNull
    private String description;

    @Positive
    @NotNull
    private BigDecimal price;

    @PastOrPresent
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime neededBefore;

    @NotNull
    private CategoryEnum category;

    public ProductDTO() {
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

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
