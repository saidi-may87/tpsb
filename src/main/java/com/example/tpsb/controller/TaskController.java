package com.example.tpsb.controller;

import com.example.tpsb.dto.TaskRequest;
import com.example.tpsb.model.Task;
import com.example.tpsb.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getOne(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task create(@RequestBody TaskRequest request) {
        return taskService.createTask(toEntity(request));
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody TaskRequest request) {
        return taskService.updateTask(id, toEntity(request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    private Task toEntity(TaskRequest request) {
        Task task = new Task(request.title(), request.description());
        task.setCompleted(request.completed());
        return task;
    }
}
