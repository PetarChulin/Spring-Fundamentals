package com.example.gira.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "classifications")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private ClassificationNameEnum name ;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Classification() {
    }

    public Classification(ClassificationNameEnum name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassificationNameEnum getName() {
        return name;
    }

    public void setName(ClassificationNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
