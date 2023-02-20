package com.example.exam.service;

import com.example.exam.model.dto.LoginUserDTO;
import com.example.exam.model.dto.RegisterUserDTO;
import com.example.exam.model.entity.Offer;
import com.example.exam.repositories.OfferRepository;
import com.example.exam.repositories.UserRepository;
import com.example.exam.session.LoggedUser;
import com.example.exam.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    private LoggedUser session;
    private LoggedUser loggedUser;
    private OfferRepository offerRepository;

    public AuthService(UserRepository userRepository, LoggedUser session, LoggedUser loggedUser, OfferRepository offerRepository) {
        this.userRepository = userRepository;
        this.session = session;
        this.loggedUser = loggedUser;
        this.offerRepository = offerRepository;
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
//        user.setOffers(user.getOffers());

        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginUserDTO loginUserDTO) {

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
