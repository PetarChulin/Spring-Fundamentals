package com.example.likebook.services;

import com.example.likebook.model.dto.PostDTO;
import com.example.likebook.model.entity.Mood;
import com.example.likebook.model.entity.MoodEnum;
import com.example.likebook.model.entity.Post;
import com.example.likebook.model.entity.User;
import com.example.likebook.repositories.MoodRepository;
import com.example.likebook.repositories.PostRepository;
import com.example.likebook.repositories.UserRepository;
import com.example.likebook.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final MoodRepository moodRepository;

    private final PostRepository postRepository;
    private UserRepository userRepository;
    private LoggedUser loggedUser;

    public PostService(MoodRepository moodRepository, PostRepository postRepository, UserRepository userRepository, LoggedUser loggedUser) {
        this.moodRepository = moodRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public boolean add(PostDTO postDTO) {

        Optional<Mood> mood = this.moodRepository.findByName(MoodEnum.valueOf(postDTO.getMood().name()));
        Optional<User> userId = this.userRepository.findById(this.loggedUser.getId());

        Post post = new Post();

        post.setContent(postDTO.getContent());
        post.setMood(mood.get());
        post.setUser(userId.get());

        this.postRepository.save(post);

        return true;
    }

    public void removePostById(Long id) {

        postRepository.deleteById(id);
    }

    public void likePost(Long id) {
        Post post = this.postRepository.findById(id).get();

        User userId = this.userRepository.getUsersById(this.loggedUser.getId());

        post.addLike(userId);

        this.postRepository.save(post);
    }
}
