package com.example.heroes.controller;

import com.example.heroes.model.dto.HeroDTO;
import com.example.heroes.model.entity.Hero;
import com.example.heroes.repositories.HeroRepository;
import com.example.heroes.repositories.UserRepository;
import com.example.heroes.service.AuthService;
import com.example.heroes.service.HeroService;
import com.example.heroes.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HeroController {

    private final AuthService authService;

    private HeroRepository heroRepository;

    private HeroService heroService;

    private UserRepository userRepository;
    private LoggedUser loggedUser;

    public HeroController(AuthService authService, HeroRepository heroRepository, HeroService heroService, UserRepository userRepository, LoggedUser loggedUser) {
        this.authService = authService;
        this.heroRepository = heroRepository;
        this.heroService = heroService;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("heroDTO")
    public HeroDTO initHeroDTO(){return new HeroDTO();}

    @GetMapping("/heroes/add")
    public String add() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "create-hero";
    }

    @PostMapping("/heroes/add")
    public String posts(@Valid HeroDTO heroDTO,
                        BindingResult result,
                        RedirectAttributes attributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors() || !this.heroService.add(heroDTO)) {
            attributes.addFlashAttribute("heroDTO", heroDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.heroDTO",
                    result);
            return "redirect:/heroes/add";
        }

        return "redirect:/home";
    }

    @GetMapping("/heroes/details/{id}")
    public ModelAndView details(@PathVariable Long id , ModelAndView model) {

        Hero hero = this.heroRepository.findById(id).orElseThrow();

        model.setViewName("details-hero");
        model.addObject("hero", hero);
        return model;
    }

    @GetMapping("/heroes/deletePage/{id}")
    public ModelAndView deletePage(@PathVariable Long id, ModelAndView model){

        Hero hero = this.heroRepository.findById(id).orElseThrow();

        model.setViewName("delete-hero");
        model.addObject("heroDelete" , hero);
        return model;
    }

    @GetMapping("/heroes/delete/{id}")
    public String delete(@PathVariable Long id){

        this.heroService.deleteHeroById(id);

        return "redirect:/home";
    }
}
