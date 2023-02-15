package com.example.gira.model.dto;

import com.example.gira.model.entity.Classification;
import com.example.gira.model.entity.ClassificationNameEnum;
import com.example.gira.model.entity.ProgressEnum;
import com.example.gira.model.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TaskDTO {

    @Size(min= 3, max = 20, message = "Name length must be between 3 and 20 characters!")
    @NotNull
    private String name;

    @Size(min = 5 , message = "Description length must be more than 5 characters!")
    @NotNull
    private String description;


    private ProgressEnum progress;

    @FutureOrPresent(message = "The date can not be in the past!")
    private LocalDate dueDate;

    @NotNull
    private ClassificationNameEnum classification;

    private User user;

    public TaskDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProgressEnum getProgress() {
        return progress;
    }

    public void setProgress(ProgressEnum progress) {
        this.progress = progress;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public ClassificationNameEnum getClassification() {
        return classification;
    }

    public void setClassification(ClassificationNameEnum classification) {
        this.classification = classification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
