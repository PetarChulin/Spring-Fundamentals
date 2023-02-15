package com.example.gira.service;

import com.example.gira.model.dto.LoginUserDTO;
import com.example.gira.model.dto.RegisterUserDTO;
import com.example.gira.model.entity.User;
import com.example.gira.repositories.UserRepository;
import com.example.gira.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    private LoggedUser session;

    public AuthService(UserRepository userRepository, LoggedUser session) {
        this.userRepository = userRepository;
        this.session = session;
    }

    public boolean register(RegisterUserDTO registerUserDTO) {
        if (!registerUserDTO.getPassword().equals(registerUserDTO.getRepeatPassword())) {
            return false;
        }

        Optional<User> email = this.userRepository.findByEmail(registerUserDTO.getEmail());
        Optional<User> username = this.userRepository.findByUsername(registerUserDTO.getUsername());

        if (email.isPresent() || username.isPresent()) {
            return false;
        }

        User user = new User();

        user.setUsername(registerUserDTO.getUsername());
        user.setPassword(registerUserDTO.getPassword());
        user.setEmail(registerUserDTO.getEmail());


        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginUserDTO loginUserDTO) {

        Optional<User> confirmCredentials = this.userRepository
                .findByEmailAndPassword(loginUserDTO.getEmail() , loginUserDTO.getPassword());

        if (confirmCredentials.isEmpty()) {
            return false;
        }

        this.session.login(confirmCredentials.get());
        return true;
    }

    public void logout() {
        this.session.logout();
    }


    public boolean isLoggedIn() {

        return this.session.getId() > 0;
    }
}
