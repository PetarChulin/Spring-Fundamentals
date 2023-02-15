package com.example.coffee_shop.services;

import com.example.coffee_shop.model.dto.LoginDTO;
import com.example.coffee_shop.model.dto.UserDTO;
import com.example.coffee_shop.model.entity.User;
import com.example.coffee_shop.repositories.UserRepository;
import com.example.coffee_shop.sessions.LoggedUser;
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

    public boolean register(UserDTO registerUserDTO) {
        if (!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())) {
            return false;
        }

        Optional<User> email = this.userRepository.findByEmail(registerUserDTO.getEmail());
        Optional<User> username = this.userRepository.findByUsername(registerUserDTO.getUsername());

        if (email.isPresent() || username.isPresent()) {
            return false;
        }

        User user = new User();

        user.setEmail(registerUserDTO.getEmail());
        user.setFirstName(registerUserDTO.getFirstName());
        user.setLastName(registerUserDTO.getLastName());
        user.setUsername(registerUserDTO.getUsername());
        user.setPassword(registerUserDTO.getPassword());

        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginUserDTO) {

        Optional<User> confirmCredentials = this.userRepository
                .findByUsernameAndPassword(loginUserDTO.getUsername(), loginUserDTO.getPassword());

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
