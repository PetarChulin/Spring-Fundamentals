package com.example.coffee_shop.model.dto;

import com.example.coffee_shop.model.entity.Category;
import com.example.coffee_shop.model.entity.CategoryEnum;
import com.example.coffee_shop.model.entity.Order;
import com.example.coffee_shop.model.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrdersDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;

    private User employee;

    public OrdersDTO(Order order) {
        this.id = order.getId();
        this.name = order.getName();
        this.price = order.getPrice();
        this.category = order.getCategory();
        this.employee = order.getEmployee();
    }

    public OrdersDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }
}
