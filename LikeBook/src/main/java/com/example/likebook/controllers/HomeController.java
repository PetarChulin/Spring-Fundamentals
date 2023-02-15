package com.example.likebook.controllers;

import com.example.likebook.model.dto.PostDTO;
import com.example.likebook.model.entity.Post;
import com.example.likebook.repositories.PostRepository;
import com.example.likebook.services.AuthService;
import com.example.likebook.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private AuthService authService;
    private LoggedUser loggedUser;
    private PostRepository postRepository;

    public HomeController(AuthService authService, LoggedUser loggedUser, PostRepository postRepository) {
        this.authService = authService;
        this.loggedUser = loggedUser;
        this.postRepository = postRepository;
    }

    @GetMapping("/home")
    public String index(Model model) {

        if (!this.authService.isLoggedIn()) {
            return "index";
        }
        long userId = this.loggedUser.getId();

        List<Post> posts =this.postRepository.findAll();
        List<Post> postsByUser = this.postRepository.findByUserId(userId);
        List<Post> postsNotByUser = this.postRepository.findByUserIdNot(userId);
        Integer allOtherPostsCount = postsNotByUser.size();

        model.addAttribute("posts" , posts);
        model.addAttribute("postsByUser" , postsByUser);
        model.addAttribute("postsNotByUser" , postsNotByUser);
        model.addAttribute("allOtherPostsCount" , allOtherPostsCount);

        return "home";
    }
}
