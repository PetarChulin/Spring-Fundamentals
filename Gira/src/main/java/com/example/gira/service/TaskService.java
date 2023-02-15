package com.example.gira.service;

import com.example.gira.model.dto.TaskDTO;
import com.example.gira.model.entity.*;
import com.example.gira.repositories.ClassificationRepository;
import com.example.gira.repositories.TaskRepository;
import com.example.gira.repositories.UserRepository;
import com.example.gira.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    private UserRepository userRepository;
    private LoggedUser loggedUser;

    private TaskRepository taskRepository;

    private ClassificationRepository classificationRepository;

    public TaskService(UserRepository userRepository, LoggedUser loggedUser, TaskRepository taskRepository, ClassificationRepository classificationRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.taskRepository = taskRepository;
        this.classificationRepository = classificationRepository;
    }

    public boolean add(TaskDTO taskDTO) {

        Optional<Task> taskByName = this.taskRepository.findByName(taskDTO.getName());
        Optional<Classification> classification = this.classificationRepository
                .findByName(ClassificationNameEnum.valueOf(taskDTO.getClassification().name()));
        Optional<User> userId = this.userRepository.findById(this.loggedUser.getId());

        if(taskByName.isPresent()){

            return false;
        }

        Task task = new Task();

        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setProgress(ProgressEnum.OPEN);
        task.setClassification(classification.get());
        task.setDueDate(taskDTO.getDueDate());
        task.setUser(userId.get());

        this.taskRepository.save(task);

        return true;
    }

    public void changeProgress(Long id) {

        Task task = this.taskRepository.findById(id).get();

        ProgressEnum progress = task.getProgress();
        if(progress == ProgressEnum.OPEN) {
        task.setProgress(ProgressEnum.IN_PROGRESS);
        } else if (progress == ProgressEnum.IN_PROGRESS) {
            task.setProgress(ProgressEnum.COMPLETED);
        } else if (progress == ProgressEnum.COMPLETED) {
            this.taskRepository.deleteById(id);
            return;
        }

        this.taskRepository.save(task);
    }




}
