package com.example.likebook.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "moods")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private MoodEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Mood() {
    }

    public Mood(MoodEnum name, String description) {

        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MoodEnum getName() {
        return name;
    }

    public void setName(MoodEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
