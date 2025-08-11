package com.example.ToDoList.commands;

import com.example.ToDoList.model.ToDoItem;
import com.example.ToDoList.model.TaskStatus;
import com.example.ToDoList.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateTaskCommandTest {

    @Mock
    private ToDoService toDoService;

    @InjectMocks
    private CreateTaskCommand createTaskCommand;

    @Test
    void testCreateTaskSuccess() {
        String name = "Test Task";
        String description = "Test Description";
        String dueDate = "2025-12-31";
        ToDoItem expectedItem = new ToDoItem(1L, name, description, LocalDate.of(2025, 12, 31));

        when(toDoService.createTodo(eq(name), eq(description), eq(LocalDate.of(2025, 12, 31))))
                .thenReturn(expectedItem);

        String result = createTaskCommand.createTask(name, description, dueDate);

        assertTrue(result.contains("Task created successfully"));
        assertTrue(result.contains("#1"));
        assertTrue(result.contains("Test Task"));
        verify(toDoService).createTodo(name, description, LocalDate.of(2025, 12, 31));
    }

    @Test
    void testCreateTaskInvalidDateFormat() {
        String name = "Test Task";
        String description = "Test Description";
        String invalidDate = "31-12-2025";

        String result = createTaskCommand.createTask(name, description, invalidDate);

        assertEquals("Invalid date format. Use: yyyy-MM-dd (example: 2025-12-31)", result);
        verify(toDoService, never()).createTodo(any(), any(), any());
    }

    @Test
    void testCreateTaskInvalidDateText() {
        String name = "Test Task";
        String description = "Test Description";
        String invalidDate = "invalid-date";

        String result = createTaskCommand.createTask(name, description, invalidDate);

        assertEquals("Invalid date format. Use: yyyy-MM-dd (example: 2025-12-31)", result);
        verify(toDoService, never()).createTodo(any(), any(), any());
    }

    @Test
    void testCreateTaskEmptyFields() {
        String name = "";
        String description = "";
        String dueDate = "2025-12-31";
        ToDoItem expectedItem = new ToDoItem(1L, name, description, LocalDate.of(2025, 12, 31));

        when(toDoService.createTodo(eq(name), eq(description), eq(LocalDate.of(2025, 12, 31))))
                .thenReturn(expectedItem);

        String result = createTaskCommand.createTask(name, description, dueDate);

        assertTrue(result.contains("Task created successfully"));
        verify(toDoService).createTodo(name, description, LocalDate.of(2025, 12, 31));
    }
}