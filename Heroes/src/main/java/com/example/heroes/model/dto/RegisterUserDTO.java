package com.example.heroes.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterUserDTO {

    @Size(min = 3 , max = 20 , message = "Username length must be between 3 and 20 characters!")
    @NotNull
    private String username;

    @NotNull
    @Size(min = 5 , max = 20 , message = "Password length must be between 5 and 20 characters!")
    private String password;

    @Size(min = 5 , max = 20, message = "Password length must be between 5 and 20 characters!")
    private String repeatPassword;

    @Email(message = "Must be valid email!")
    private String email;

    @Size(min = 5 , max = 20, message = "Country length must be between 5 and 20 characters!")
    private String country;

    public RegisterUserDTO() {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
