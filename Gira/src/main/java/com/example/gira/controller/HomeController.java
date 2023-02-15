package com.example.gira.controller;

import com.example.gira.model.entity.Task;
import com.example.gira.repositories.TaskRepository;
import com.example.gira.service.AuthService;
import com.example.gira.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private AuthService authService;
    private LoggedUser loggedUser;
    private TaskRepository taskRepository;

    public HomeController(AuthService authService, LoggedUser loggedUser, TaskRepository taskRepository) {
        this.authService = authService;
        this.loggedUser = loggedUser;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/home")
    public String index(Model model) {

        if (!this.authService.isLoggedIn()) {
            return "index";
        }
        long userId = this.loggedUser.getId();

        List<Task> tasks = this.taskRepository.findAll();

        model.addAttribute("tasks", tasks);


        return "home";
    }
}
