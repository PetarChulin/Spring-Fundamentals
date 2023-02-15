package com.example.likebook.controllers;

import com.example.likebook.model.dto.PostDTO;
import com.example.likebook.model.entity.Post;
import com.example.likebook.model.entity.User;
import com.example.likebook.repositories.PostRepository;
import com.example.likebook.repositories.UserRepository;
import com.example.likebook.services.AuthService;
import com.example.likebook.services.PostService;
import com.example.likebook.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class PostController {

    private final AuthService authService;

    private PostRepository postRepository;
    private final PostService postService;
    private UserRepository userRepository;
    private LoggedUser loggedUser;

    public PostController(AuthService authService, PostRepository postRepository, PostService postService, UserRepository userRepository, LoggedUser loggedUser) {
        this.authService = authService;
        this.postRepository = postRepository;
        this.postService = postService;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("postDTO")
    public PostDTO initPostDTO(){return new PostDTO();}

//    @GetMapping("/")
//    public String index() {
//
//        if (!this.authService.isLoggedIn()) {
//            return "redirect:/index";
//        }
//
//        return "index";
//    }

    @GetMapping("/posts/add")
    public String add() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "post-add";
    }

    @PostMapping("/posts/add")
    public String posts(@Valid PostDTO postDTO,
                        BindingResult result,
                        RedirectAttributes attributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (result.hasErrors() || !this.postService.add(postDTO)) {
            attributes.addFlashAttribute("postDTO", postDTO);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.postDTO",
                    result);
            return "redirect:/posts/add";
        }

        return "redirect:/home";
    }

    @GetMapping("/posts/remove/{id}")
    public String remove(@PathVariable Long id){

        postService.removePostById(id);

        return "redirect:/home";
    }

    @GetMapping("/posts/like/{id}")
    public String like(@PathVariable Long id){

        this.postService.likePost(id);

        return "redirect:/home";
    }
}
