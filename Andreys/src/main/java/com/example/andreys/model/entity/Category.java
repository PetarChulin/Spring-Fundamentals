package com.example.andreys.model.entity;


import jakarta.persistence.*;

@Table
@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private CategoryEnum name;

    @Column(name = "description" ,columnDefinition = "TEXT")
    private String description;


    public Category() {
    }

    public Category(CategoryEnum name, String description) {
        this.name = name;
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryEnum getName() {
        return name;
    }

    public void setName(CategoryEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
