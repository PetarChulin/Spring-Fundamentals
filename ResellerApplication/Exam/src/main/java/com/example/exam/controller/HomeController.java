package com.example.exam.controller;

import com.example.exam.model.entity.Offer;
import com.example.exam.repositories.OfferRepository;
import com.example.exam.repositories.UserRepository;
import com.example.exam.service.AuthService;
import com.example.exam.service.OfferService;
import com.example.exam.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private AuthService authService;

    private UserRepository userRepository;

    private LoggedUser loggedUser;
    private OfferRepository offerRepository;
    private OfferService offerService;

    public HomeController(AuthService authService, UserRepository userRepository, LoggedUser loggedUser, OfferRepository offerRepository, OfferService offerService) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.offerRepository = offerRepository;
        this.offerService = offerService;
    }

    @GetMapping("/home")
    public String index(Model model) {

        if (!this.authService.isLoggedIn()) {
            return "index";
        }

        long userId = this.loggedUser.getId();


        List<Offer> offersByUser = this.offerRepository.findByCreatedById(userId);
        List<Offer> offersNotByUser = this.offerRepository.findByCreatedByIdNot(userId);
        List<Offer> boughtByUser = this.userRepository.getUserBoughtOffers(userId);
        Integer allOthersCount = offersNotByUser.size();

        model.addAttribute("offersByUser" , offersByUser);
        model.addAttribute("offersNotByUser" , offersNotByUser);
        model.addAttribute("allOthersCount" , allOthersCount);
        model.addAttribute("boughtByUser" , boughtByUser);


        return "home";
    }
}
