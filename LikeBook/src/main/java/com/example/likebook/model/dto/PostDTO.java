package com.example.likebook.model.dto;

import com.example.likebook.model.entity.MoodEnum;
import com.example.likebook.model.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostDTO {

    @NotNull
    @Size(min = 2, max = 150, message = "Content must be between 2 and 150 characters!")
    private String content;

    @NotNull(message = "You must select a mood!")
    private MoodEnum mood;

    private User likes;

    private Integer countLikes;

    public PostDTO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MoodEnum getMood() {
        return mood;
    }

    public void setMood(MoodEnum mood) {
        this.mood = mood;
    }

    public User getLikes() {
        return likes;
    }

    public void setLikes(User likes) {
        this.likes = likes;
    }

    public Integer getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(Integer countLikes) {
        this.countLikes = countLikes;
    }
}
