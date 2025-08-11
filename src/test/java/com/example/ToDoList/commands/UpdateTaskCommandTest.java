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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateTaskCommandTest {

    @Mock
    private ToDoService toDoService;

    @InjectMocks
    private UpdateTaskCommand updateTaskCommand;

    @Test
    void testUpdateTaskName() {
        String taskId = "1";
        ToDoItem existingTask = new ToDoItem(1L, "Old Name", TaskStatus.TODO, "Description", LocalDate.of(2025, 12, 31));

        when(toDoService.getTodoById(taskId)).thenReturn(Optional.of(existingTask));
        when(toDoService.updateTodo(any(ToDoItem.class))).thenReturn(true);

        String result = updateTaskCommand.updateTask(taskId, "New Name", "", "", "");

        assertTrue(result.contains("Task updated successfully"));
        assertEquals("New Name", existingTask.getName());
        verify(toDoService).getTodoById(taskId);
        verify(toDoService).updateTodo(existingTask);
    }

    @Test
    void testUpdateTaskDescription() {
        String taskId = "1";
        ToDoItem existingTask = new ToDoItem(1L, "Name", TaskStatus.TODO, "Old Description", LocalDate.of(2025, 12, 31));

        when(toDoService.getTodoById(taskId)).thenReturn(Optional.of(existingTask));
        when(toDoService.updateTodo(any(ToDoItem.class))).thenReturn(true);

        String result = updateTaskCommand.updateTask(taskId, "", "New Description", "", "");

        assertTrue(result.contains("Task updated successfully"));
        assertEquals("New Description", existingTask.getDescription());
        verify(toDoService).updateTodo(existingTask);
    }

    @Test
    void testUpdateTaskDueDate() {
        String taskId = "1";
        ToDoItem existingTask = new ToDoItem(1L, "Name", TaskStatus.TODO, "Description", LocalDate.of(2025, 12, 31));

        when(toDoService.getTodoById(taskId)).thenReturn(Optional.of(existingTask));
        when(toDoService.updateTodo(any(ToDoItem.class))).thenReturn(true);

        String result = updateTaskCommand.updateTask(taskId, "", "", "2026-01-15", "");

        assertTrue(result.contains("Task updated successfully"));
        assertEquals(LocalDate.of(2026, 1, 15), existingTask.getDueDate());
        verify(toDoService).updateTodo(existingTask);
    }

    @Test
    void testUpdateTaskStatus() {
        String taskId = "1";
        ToDoItem existingTask = new ToDoItem(1L, "Name", TaskStatus.TODO, "Description", LocalDate.of(2025, 12, 31));

        when(toDoService.getTodoById(taskId)).thenReturn(Optional.of(existingTask));
        when(toDoService.updateTodo(any(ToDoItem.class))).thenReturn(true);

        String result = updateTaskCommand.updateTask(taskId, "", "", "", "COMPLETED");

        assertTrue(result.contains("Task updated successfully"));
        assertEquals(TaskStatus.COMPLETED, existingTask.getStatus());
        verify(toDoService).updateTodo(existingTask);
    }

    @Test
    void testUpdateTaskMultipleFields() {
        String taskId = "1";
        ToDoItem existingTask = new ToDoItem(1L, "Old Name", TaskStatus.TODO, "Old Description", LocalDate.of(2025, 12, 31));

        when(toDoService.getTodoById(taskId)).thenReturn(Optional.of(existingTask));
        when(toDoService.updateTodo(any(ToDoItem.class))).thenReturn(true);

        String result = updateTaskCommand.updateTask(taskId, "New Name", "New Description", "2026-01-15", "IN_PROGRESS");

        assertTrue(result.contains("Task updated successfully"));
        assertEquals("New Name", existingTask.getName());
        assertEquals("New Description", existingTask.getDescription());
        assertEquals(LocalDate.of(2026, 1, 15), existingTask.getDueDate());
        assertEquals(TaskStatus.IN_PROGRESS, existingTask.getStatus());
        verify(toDoService).updateTodo(existingTask);
    }

    @Test
    void testUpdateTaskNotFound() {
        String taskId = "999";

        when(toDoService.getTodoById(taskId)).thenReturn(Optional.empty());

        String result = updateTaskCommand.updateTask(taskId, "New Name", "", "", "");

        assertEquals("No task found with that ID.", result);
        verify(toDoService).getTodoById(taskId);
        verify(toDoService, never()).updateTodo(any());
    }

    @Test
    void testUpdateTaskNoFieldsProvided() {
        String taskId = "1";
        ToDoItem existingTask = new ToDoItem(1L, "Name", TaskStatus.TODO, "Description", LocalDate.of(2025, 12, 31));

        when(toDoService.getTodoById(taskId)).thenReturn(Optional.of(existingTask));

        String result = updateTaskCommand.updateTask(taskId, "", "", "", "");

        assertEquals("No fields to update. Provide at least one: --name, --description, --due-date, or --status", result);
        verify(toDoService).getTodoById(taskId);
        verify(toDoService, never()).updateTodo(any());
    }

    @Test
    void testUpdateTaskInvalidDateFormat() {
        String taskId = "1";
        ToDoItem existingTask = new ToDoItem(1L, "Name", TaskStatus.TODO, "Description", LocalDate.of(2025, 12, 31));

        when(toDoService.getTodoById(taskId)).thenReturn(Optional.of(existingTask));

        String result = updateTaskCommand.updateTask(taskId, "", "", "invalid-date", "");

        assertEquals("Invalid date format. Use: yyyy-MM-dd (example: 2025-12-31)", result);
        verify(toDoService).getTodoById(taskId);
        verify(toDoService, never()).updateTodo(any());
    }

    @Test
    void testUpdateTaskInvalidStatus() {
        String taskId = "1";
        ToDoItem existingTask = new ToDoItem(1L, "Name", TaskStatus.TODO, "Description", LocalDate.of(2025, 12, 31));

        when(toDoService.getTodoById(taskId)).thenReturn(Optional.of(existingTask));

        String result = updateTaskCommand.updateTask(taskId, "", "", "", "INVALID_STATUS");

        assertEquals("Invalid status. Use: TODO, IN_PROGRESS or COMPLETED", result);
        verify(toDoService).getTodoById(taskId);
        verify(toDoService, never()).updateTodo(any());
    }

    @Test
    void testUpdateTaskSaveError() {
        String taskId = "1";
        ToDoItem existingTask = new ToDoItem(1L, "Name", TaskStatus.TODO, "Description", LocalDate.of(2025, 12, 31));

        when(toDoService.getTodoById(taskId)).thenReturn(Optional.of(existingTask));
        when(toDoService.updateTodo(any(ToDoItem.class))).thenReturn(false);

        String result = updateTaskCommand.updateTask(taskId, "New Name", "", "", "");

        assertEquals("Error saving task updates.", result);
        verify(toDoService).updateTodo(existingTask);
    }
}