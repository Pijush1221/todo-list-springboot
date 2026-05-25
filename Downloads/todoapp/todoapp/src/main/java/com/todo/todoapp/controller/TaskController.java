package com.todo.todoapp.controller;

import com.todo.todoapp.models.Task;
import com.todo.todoapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    public String getTasks(Model model){
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks",tasks);
        return "tasks";
    }

    @PostMapping
    public String createTask(@RequestParam String title,
                             @RequestParam(required = false) String description) {
        taskService.createTask(title, description);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String removeTask(@PathVariable Integer id){
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Integer id){
        taskService.toggleTask(id);
        return "redirect:/";
    }
}