package com.app.domain.service.impl;

import com.app.domain.model.Task;
import com.app.domain.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }

    @Override
    public Task createTask(Task task) {
        task.setId(idCounter++);
        tasks.add(task);
        return task;
    }
}
