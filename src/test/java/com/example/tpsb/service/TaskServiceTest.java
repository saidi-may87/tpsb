package com.example.tpsb.service;

import com.example.tpsb.model.Task;
import com.example.tpsb.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void getAllTasks_returnsList() {
        when(taskRepository.findAll()).thenReturn(List.of(new Task("A", "desc")));
        assertEquals(1, taskService.getAllTasks().size());
    }

    @Test
    void getTaskById_found() {
        Task t = new Task("A", "desc");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(t));
        assertEquals("A", taskService.getTaskById(1L).getTitle());
    }

    @Test
    void getTaskById_notFound_throws() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> taskService.getTaskById(99L));
    }

    @Test
    void createTask_saves() {
        Task t = new Task("A", "desc");
        when(taskRepository.save(t)).thenReturn(t);
        assertEquals("A", taskService.createTask(t).getTitle());
        verify(taskRepository).save(t);
    }

    @Test
    void deleteTask_callsRepository() {
        taskService.deleteTask(1L);
        verify(taskRepository).deleteById(1L);
    }
}
