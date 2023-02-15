package com.example.coffee_shop.model.dto;

import com.example.coffee_shop.model.entity.Order;

import java.util.Set;

public class UsersDTO {

    private String username;
    private Integer countOrders;
    public UsersDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCountOrders() {
        return countOrders;
    }

    public void setCountOrders(Integer countOrders) {
        this.countOrders = countOrders;
    }
}
