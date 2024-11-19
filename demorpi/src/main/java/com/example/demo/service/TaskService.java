package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> listarTodos() {
        return taskRepository.findAll();
    }

    public List<Task> listarPorPrioridade(int prioridade) {
        return taskRepository.findByPrioridade(prioridade);
    }

    public Task salvar(Task task) {
        return taskRepository.save(task);
    }

    public void deletarporId(Long Id) {
        if (taskRepository.existsById(Id)) {
            taskRepository.deleteById(Id);
        }  else {
            throw new EntityNotFoundException("Task com ID " + Id + " não encontrada.");
        }
    }

    public Task atualizarTask(Long id, Task taskAtualizada) {
        Task taskExistente = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task com ID " + id + " não encontrada."));

        // Atualize os campos
        taskExistente.setNomeTarefa(taskAtualizada.getNomeTarefa());
        taskExistente.setDescricao(taskAtualizada.getDescricao());
        taskExistente.setStatus(taskAtualizada.getStatus());

        return taskRepository.save(taskExistente);
    }

    public List<Task> listarPorStatus(Task.Status status) {
        return taskRepository.findByStatus(status);
    }
}
