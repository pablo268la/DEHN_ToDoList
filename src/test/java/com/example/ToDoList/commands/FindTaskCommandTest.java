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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindTaskCommandTest {

    @Mock
    private ToDoService toDoService;

    @InjectMocks
    private FindTaskCommand findTaskCommand;

    @Test
    void testFindTaskSuccess() {
        String taskId = "1";
        ToDoItem task = new ToDoItem(1L, "Test Task", TaskStatus.TODO, "Test Description", LocalDate.of(2025, 12, 31));

        when(toDoService.getTodoById(taskId)).thenReturn(Optional.of(task));

        String result = findTaskCommand.findTask(taskId);

        assertTrue(result.contains("Task found:"));
        verify(toDoService).getTodoById(taskId);
    }

    @Test
    void testFindTaskNotFound() {
        String taskId = "999";

        when(toDoService.getTodoById(taskId)).thenReturn(Optional.empty());

        String result = findTaskCommand.findTask(taskId);

        assertEquals("No task found with that ID.", result);
        verify(toDoService).getTodoById(taskId);
    }

    @Test
    void testFindTaskInvalidId() {
        String taskId = "invalid";

        when(toDoService.getTodoById(taskId)).thenReturn(Optional.empty());

        String result = findTaskCommand.findTask(taskId);

        assertEquals("No task found with that ID.", result);
        verify(toDoService).getTodoById(taskId);
    }

    @Test
    void testFindTaskEmptyId() {
        String taskId = "";

        when(toDoService.getTodoById(taskId)).thenReturn(Optional.empty());

        String result = findTaskCommand.findTask(taskId);

        assertEquals("No task found with that ID.", result);
        verify(toDoService).getTodoById(taskId);
    }
}