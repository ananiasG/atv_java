package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/prio")
    public List<Task> listarPorPrioridade(int prioridade) {
        return taskRepository.findByPrioridade(prioridade);
    }

    //mostrar todas as tarefas de uma vez
    @GetMapping
    public List<Task> listarTodos() {
        return taskService.listarTodos();
    }

    // salvar uma tarefa
    @PostMapping
    public Task salvar(@RequestBody Task task) {
        return taskService.salvar(task);
    }

    // mostrar as tarefas pelos status
    @GetMapping("/status/{status}")
    public List<Task> listarPorStatus(@PathVariable Task.Status status) {
        return taskService.listarPorStatus(status);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deletarTask(@PathVariable Long id) {
        taskService.deletarporId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> atualizarTask(@PathVariable Long id, @RequestBody Task task) {
        Task taskAtualizada = taskService.atualizarTask(id, task);
        return ResponseEntity.ok(taskAtualizada);
    }
}
