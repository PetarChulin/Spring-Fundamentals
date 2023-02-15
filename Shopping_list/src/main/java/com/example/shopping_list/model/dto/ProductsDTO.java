package com.example.shopping_list.model.dto;

import com.example.shopping_list.model.entity.Category;
import com.example.shopping_list.model.entity.CategoryEnum;
import com.example.shopping_list.model.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductsDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private LocalDateTime neededBefore;
    private Long category;

    public ProductsDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.neededBefore = product.getNeededBefore();
        this.category = product.getCategory().getId();
    }

    public ProductsDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }
}
