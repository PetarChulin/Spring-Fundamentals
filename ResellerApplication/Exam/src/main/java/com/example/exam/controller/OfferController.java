package com.example.exam.controller;

import com.example.exam.model.dto.OfferDTO;
import com.example.exam.repositories.OfferRepository;
import com.example.exam.repositories.UserRepository;
import com.example.exam.service.AuthService;
import com.example.exam.service.OfferService;
import com.example.exam.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OfferController {

    private final AuthService authService;

    private UserRepository userRepository;
    private LoggedUser loggedUser;

    private OfferRepository offerRepository;

    private OfferService offerService;


    public OfferController(AuthService authService, UserRepository userRepository, LoggedUser loggedUser, OfferRepository offerRepository, OfferService offerService) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.offerRepository = offerRepository;
        this.offerService = offerService;
    }

    @ModelAttribute("offerDTO")
    public OfferDTO initOffertDTO(){return new OfferDTO();}

    @GetMapping("/offers/add")
    public String add() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "offer-add";
    }

    @PostMapping("/offers/add")
    public String posts(@Valid OfferDTO offerDTO,
                        BindingResult result,
                        RedirectAttributes attributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors() || !this.offerService.add(offerDTO)) {
            attributes.addFlashAttribute("offerDTO", offerDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.offerDTO",
                    result);
            return "redirect:/offers/add";
        }

        return "redirect:/home";
    }

    @GetMapping("/offers/remove/{id}")
    public String remove(@PathVariable Long id){

        offerService.removeOfferById(id);

        return "redirect:/home";
    }

    @GetMapping("/offers/buy/{id}")
    public String buy(@PathVariable Long id){

        offerService.buyOffer(id);

        return "redirect:/home";
    }




}
