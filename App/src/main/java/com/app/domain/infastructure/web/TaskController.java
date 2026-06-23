package com.app.domain.infastructure.web;

import com.app.domain.model.Task;
import com.app.domain.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAll(){
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task create(@RequestBody Task task){
        return taskService.createTask(task);
    }

}
