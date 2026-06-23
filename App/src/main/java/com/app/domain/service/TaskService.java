package com.app.domain.service;

import com.app.domain.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task createTask(Task task);
}
