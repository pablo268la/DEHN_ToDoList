package com.example.ToDoList.commands;

import com.example.ToDoList.model.TaskStatus;
import com.example.ToDoList.model.ToDoItem;
import com.example.ToDoList.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListTasksCommandTest {

    @Mock
    private ToDoService toDoService;

    @InjectMocks
    private ListTasksCommand listTasksCommand;

    @Test
    void testListAllTasks() {
        List<ToDoItem> tasks = List.of(
                new ToDoItem(1L, "Task 1", TaskStatus.TODO, "Description 1", LocalDate.of(2025, 12, 31)),
                new ToDoItem(2L, "Task 2", TaskStatus.IN_PROGRESS, "Description 2", LocalDate.of(2025, 12, 30))
        );

        when(toDoService.getAllTodos()).thenReturn(tasks);

        String result = listTasksCommand.listTasks("ALL");

        assertTrue(result.contains("Task 1"));
        assertTrue(result.contains("Task 2"));
        verify(toDoService).getAllTodos();
        verify(toDoService, never()).getTodosByStatus(any());
    }

    @Test
    void testListTasksByStatusTodo() {
        List<ToDoItem> todoTasks = List.of(
                new ToDoItem(1L, "Task 1", TaskStatus.TODO, "Description 1", LocalDate.of(2025, 12, 31))

        );

        when(toDoService.getTodosByStatus(TaskStatus.TODO)).thenReturn(todoTasks);

        String result = listTasksCommand.listTasks("TODO");

        assertTrue(result.contains("Task 1"));
        verify(toDoService).getTodosByStatus(TaskStatus.TODO);
        verify(toDoService, never()).getAllTodos();
    }

    @Test
    void testListTasksByStatusInProgress() {
        List<ToDoItem> inProgressTasks = List.of(
                new ToDoItem(2L, "Task 2", TaskStatus.IN_PROGRESS, "Description 2", LocalDate.of(2025, 12, 30))
        );

        when(toDoService.getTodosByStatus(TaskStatus.IN_PROGRESS)).thenReturn(inProgressTasks);

        String result = listTasksCommand.listTasks("IN_PROGRESS");

        assertTrue(result.contains("Task 2"));
        verify(toDoService).getTodosByStatus(TaskStatus.IN_PROGRESS);
    }

    @Test
    void testListTasksByStatusCompleted() {
        List<ToDoItem> completedTasks = List.of(
                new ToDoItem(3L, "Task 3", TaskStatus.COMPLETED, "Description 3", LocalDate.of(2025, 12, 29))
        );

        when(toDoService.getTodosByStatus(TaskStatus.COMPLETED)).thenReturn(completedTasks);

        String result = listTasksCommand.listTasks("COMPLETED");

        assertTrue(result.contains("Task 3"));
        verify(toDoService).getTodosByStatus(TaskStatus.COMPLETED);
    }

    @Test
    void testListTasksEmptyList() {
        when(toDoService.getAllTodos()).thenReturn(Collections.emptyList());

        String result = listTasksCommand.listTasks("ALL");

        assertEquals("No tasks in the list.", result);
        verify(toDoService).getAllTodos();
    }

    @Test
    void testListTasksInvalidStatus() {
        String result = listTasksCommand.listTasks("INVALID_STATUS");

        assertEquals("Invalid status. Use: TODO, IN_PROGRESS, COMPLETED or ALL", result);
        verify(toDoService, never()).getAllTodos();
        verify(toDoService, never()).getTodosByStatus(any());
    }
}