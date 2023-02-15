package com.example.coffee_shop.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name="categories")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum name;

    @Column(name="needed_time" , nullable = false)
    private Integer neededTime;

    public Category() {
    }

    public Category(CategoryEnum name, Integer neededTime) {
        this.name = name;
        this.neededTime = neededTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategoryEnum getName() {
        return name;
    }

    public void setName(CategoryEnum name) {
        this.name = name;
    }

    public Integer getNeededTime() {
        return neededTime;
    }

    public void setNeededTime(Integer neededTime) {
        this.neededTime = neededTime;
    }
}
