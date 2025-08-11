package com.example.ToDoList.commands;

import com.example.ToDoList.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteTaskCommandTest {

    @Mock
    private ToDoService toDoService;

    @InjectMocks
    private DeleteTaskCommand deleteTaskCommand;

    @Test
    void testDeleteTask_WithId_Success() {
        String taskId = "1";
        when(toDoService.deleteTodo(taskId)).thenReturn(true);

        String result = deleteTaskCommand.deleteTask(taskId, false);

        assertEquals("Task deleted successfully.", result);
        verify(toDoService).deleteTodo(taskId);
        verify(toDoService, never()).deleteAllTodos();
    }

    @Test
    void testDeleteTask_WithId_NotFound() {
        String taskId = "999";
        when(toDoService.deleteTodo(taskId)).thenReturn(false);

        String result = deleteTaskCommand.deleteTask(taskId, false);

        assertEquals("No task found with that ID.", result);
        verify(toDoService).deleteTodo(taskId);
    }

    @Test
    void testDeleteTask_All_WithTasks() {
        when(toDoService.deleteAllTodos()).thenReturn(5);

        String result = deleteTaskCommand.deleteTask(null, true);

        assertEquals("Successfully deleted all 5 tasks.", result);
        verify(toDoService).deleteAllTodos();
        verify(toDoService, never()).deleteTodo(any());
    }

    @Test
    void testDeleteTask_All_NoTasks() {
        when(toDoService.deleteAllTodos()).thenReturn(0);

        String result = deleteTaskCommand.deleteTask(null, true);

        assertEquals("No tasks to delete.", result);
        verify(toDoService).deleteAllTodos();
    }

    @Test
    void testDeleteTask_BothIdAndAll_Error() {
        String result = deleteTaskCommand.deleteTask("1", true);

        assertEquals("Error: Cannot use both --id and --all options together. Choose one.", result);
        verify(toDoService, never()).deleteTodo(any());
        verify(toDoService, never()).deleteAllTodos();
    }

    @Test
    void testDeleteTask_NoOptions_Error() {
        String result = deleteTaskCommand.deleteTask(null, false);

        assertEquals("Error: You must specify either --id <task_id> or --all to delete tasks.", result);
        verify(toDoService, never()).deleteTodo(any());
        verify(toDoService, never()).deleteAllTodos();
    }
}