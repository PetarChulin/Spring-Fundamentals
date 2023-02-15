package com.example.andreys.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginDTO {

    @Size(min=2 , message = "Username must be more than two characters!")
    private String username;

    @Size(min = 2 , message = "Password must be more than two characters!")
    private String password;

    public LoginDTO() {
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
}
