package com.example.gira.controller;

import com.example.gira.model.dto.TaskDTO;
import com.example.gira.repositories.TaskRepository;
import com.example.gira.repositories.UserRepository;
import com.example.gira.service.AuthService;
import com.example.gira.service.TaskService;
import com.example.gira.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TaskController {

    private final AuthService authService;

    private TaskRepository taskRepository;
    private final TaskService taskService;
    private UserRepository userRepository;
    private LoggedUser loggedUser;

    public TaskController(AuthService authService, TaskRepository taskRepository, TaskService taskService, UserRepository userRepository, LoggedUser loggedUser) {
        this.authService = authService;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("taskDTO")
    public TaskDTO initPostDTO(){return new TaskDTO();}

    @GetMapping("/tasks/add")
    public String add() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "add-task";
    }

    @PostMapping("/tasks/add")
    public String posts(@Valid TaskDTO taskDTO,
                        BindingResult result,
                        RedirectAttributes attributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors() || !this.taskService.add(taskDTO)) {
            attributes.addFlashAttribute("taskDTO", taskDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.taskDTO",
                    result);
            return "redirect:/tasks/add";
        }

        return "redirect:/home";
    }

    @GetMapping("/tasks/progress/{id}")
    public String like(@PathVariable Long id){

        this.taskService.changeProgress(id);

        return "redirect:/home";
    }
}
