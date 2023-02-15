package com.example.likebook.services;

import com.example.likebook.model.dto.LoginUserDTO;
import com.example.likebook.model.dto.RegisterUserDTO;
import com.example.likebook.model.entity.User;
import com.example.likebook.repositories.UserRepository;
import com.example.likebook.session.LoggedUser;
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
                .findByUsernameAndPassword(loginUserDTO.getUsername() , loginUserDTO.getPassword());

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
