package com.example.andreys.model.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class UserDTO {

    @Size(min = 2, message = "Username must be more than two characters!")
    private String username;
    @Email(message = "Incorrect email!")
    private String email;

    @Positive(message = "Budget must be greater or equal to 0!")
    private BigDecimal budget;

    @Size(min = 2, message = "Password must be more than two characters!")
    private String password;

    @Size(min = 2)
    private String repeatPassword;


    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
