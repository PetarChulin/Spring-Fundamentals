package com.example.gira.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginUserDTO {

    @NotEmpty(message = "Email can not be empty!")
    @Email
    private String email;

    @NotNull
    @Size(min = 3 , max = 20 , message = "Password length must be between 3 and 20 characters!")
    private String password;

    @Size(min = 3 , max = 20)
    private String repeatPassword;


    public LoginUserDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
