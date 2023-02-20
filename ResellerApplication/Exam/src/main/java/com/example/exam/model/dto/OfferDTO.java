package com.example.exam.model.dto;

import com.example.exam.model.entity.ConditionEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class OfferDTO {

    private Long id;
    @Size(min=2 , max = 50 , message = "Description length must be between 2 and 50 characters!")
    @NotNull
    private String description;

    @Positive(message = "Price must be positive number")
    @NotNull
    private BigDecimal price;

    @NotNull(message = "You must select condition")
    private ConditionEnum condition;

    public OfferDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ConditionEnum getCondition() {
        return condition;
    }

    public void setCondition(ConditionEnum condition) {
        this.condition = condition;
    }
}
