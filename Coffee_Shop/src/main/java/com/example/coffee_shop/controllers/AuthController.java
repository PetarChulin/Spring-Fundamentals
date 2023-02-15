package com.example.coffee_shop.controllers;

import com.example.coffee_shop.model.dto.LoginDTO;
import com.example.coffee_shop.model.dto.UserDTO;
import com.example.coffee_shop.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("userRegisterDTO")
    public UserDTO initUserDTO() {
        return new UserDTO();
    }

    @ModelAttribute("userLoginDTO")
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
    public String register(@Valid UserDTO userRegisterDTO,
                           BindingResult result,
                           RedirectAttributes attributes) {
        if (this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors() || !this.authService.register(userRegisterDTO)) {
            attributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO",
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
    public String login(@Valid LoginDTO userLoginDTO,
                        BindingResult result,
                        RedirectAttributes attributes) {

        if (this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            attributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO",
                    result);
            return "redirect:/login";
        }

        if (!this.authService.login(userLoginDTO)) {
            attributes.addFlashAttribute("userLoginDTO", userLoginDTO);
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
