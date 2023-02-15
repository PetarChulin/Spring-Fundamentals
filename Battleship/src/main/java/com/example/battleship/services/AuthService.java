package com.example.battleship.services;

import com.example.battleship.models.dto.LoginDTO;
import com.example.battleship.models.dto.UserRegistrationDTO;
import com.example.battleship.models.entity.User;
import com.example.battleship.repos.UserRepository;
import com.example.battleship.sessions.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final LoggedUser session;

    public AuthService(UserRepository userRepository , LoggedUser session ) {
        this.userRepository = userRepository;
        this.session = session;
    }

    public boolean register(UserRegistrationDTO registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            return false;
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }
        Optional<User> byUsername = this.userRepository.findByUsername(registrationDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        user.setFullName(registrationDTO.getFullName());
        user.setPassword(registrationDTO.getPassword());

        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO) {

        Optional<User> user = this.userRepository
                .findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if(user.isEmpty()){
            return false;
        }

        this.session.login(user.get());
        return true;
    }

    public void logout() {
        this.session.logout();
    }

    public boolean isLoggedIn() {

        return this.session.getId() > 0;
    }
}
