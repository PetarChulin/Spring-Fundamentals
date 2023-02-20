package com.example.exam.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name="conditions")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ConditionEnum name;

    @Column(columnDefinition = "TEXT" , nullable = false)
    private String description;

    public Condition() {
    }

    public Condition(ConditionEnum name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConditionEnum getName() {
        return name;
    }

    public void setName(ConditionEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
