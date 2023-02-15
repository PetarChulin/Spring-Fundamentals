package com.example.heroes.controller;

import com.example.heroes.model.dto.LoginUserDTO;
import com.example.heroes.model.dto.RegisterUserDTO;
import com.example.heroes.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final AuthService authService;



    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }



    @ModelAttribute("registerUserDTO")
    public RegisterUserDTO initUserDTO() {
        return new RegisterUserDTO();
    }

    @ModelAttribute("loginUserDTO")
    public LoginUserDTO initLoginDTO() {
        return new LoginUserDTO();
    }

    @GetMapping("/register")
    public String register() {

        if (this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        return "register";

    }

    @PostMapping("/register")
    public String register(@Valid RegisterUserDTO registerUserDTO,
                           BindingResult result,
                           RedirectAttributes attributes) {
        if (this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors() || !this.authService.register(registerUserDTO)) {
            attributes.addFlashAttribute("registerUserDTO", registerUserDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.registerUserDTO",
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
    public String login(@Valid LoginUserDTO loginUserDTO,
                        BindingResult result,
                        RedirectAttributes attributes) {

        if (this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            attributes.addFlashAttribute("loginUserDTO", loginUserDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.loginUserDTO",
                    result);
            return "redirect:/login";
        }

        if (!this.authService.login(loginUserDTO)) {
            attributes.addFlashAttribute("loginUserDTO", loginUserDTO);
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
