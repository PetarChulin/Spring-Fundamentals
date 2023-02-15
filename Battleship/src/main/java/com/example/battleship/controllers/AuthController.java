package com.example.battleship.controllers;

import com.example.battleship.models.dto.LoginDTO;
import com.example.battleship.models.dto.UserRegistrationDTO;
import com.example.battleship.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @ModelAttribute("registerDTO")
    public UserRegistrationDTO initRegisterDTO() {
        return new UserRegistrationDTO();
    }

    @ModelAttribute("loginDTO")
    public LoginDTO initLoginDTO() {
        return new LoginDTO();
    }



    @GetMapping("/register")
    public String register() {

        if (this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO registerDTO,
                           BindingResult result,
                           RedirectAttributes attributes) {
        if (this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors() || !this.authService.register(registerDTO)) {
            attributes.addFlashAttribute("registerDTO", registerDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO",
                    result);
            return "redirect:/register";
        }
        return "redirect:/login";

    }

    @GetMapping("/login")
    public String login() {

        if (this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult result,
                        RedirectAttributes attributes) {

        if (this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            attributes.addFlashAttribute("loginDTO", loginDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO",
                    result);
            return "redirect:/login";
        }



        if (!this.authService.login(loginDTO)) {
            attributes.addFlashAttribute("loginDTO", loginDTO);
            attributes.addFlashAttribute("badCredentials",
                    true);
            return "redirect:/login";
        }
        return "redirect:/home";

    }
    @GetMapping("/logout")
    public String logout() {
        this.authService.logout();

        return "redirect:/";
    }

}
