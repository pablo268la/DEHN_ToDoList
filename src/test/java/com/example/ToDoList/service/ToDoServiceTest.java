package com.example.ToDoList.service;

import com.example.ToDoList.config.FileConfig;
import com.example.ToDoList.model.TaskStatus;
import com.example.ToDoList.model.ToDoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {

    @Mock
    private FileManager fileManager;

    @Mock
    private FileConfig fileConfig;

    @InjectMocks
    private ToDoService toDoService;

    private final String testFileName = "test-todos.json";

    @BeforeEach
    void setUp() {
        when(fileConfig.getFileName()).thenReturn(testFileName);
    }

    private void initializeServiceWithEmptyList() {
        when(fileManager.loadTodos(testFileName)).thenReturn(new ArrayList<>());

        toDoService.init();
    }

    @Test
    void testCreateTodo_Success() {
        initializeServiceWithEmptyList();
        String name = "Test Task";
        String description = "Test Description";
        LocalDate dueDate = LocalDate.of(2025, 12, 31);

        ToDoItem result = toDoService.createTodo(name, description, dueDate);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(name, result.getName());
        assertEquals(description, result.getDescription());
        assertEquals(dueDate, result.getDueDate());
        assertEquals(TaskStatus.TODO, result.getStatus());

        verify(fileManager).saveTodos(eq(testFileName), any());
    }

    @Test
    void testGetTodoById_ValidId_ReturnsItem() {
        initializeServiceWithEmptyList();
        ToDoItem created = toDoService.createTodo("Task", "Description", LocalDate.now());

        Optional<ToDoItem> result = toDoService.getTodoById("1");

        assertTrue(result.isPresent());
        assertEquals(created.getId(), result.get().getId());
        assertEquals(created.getName(), result.get().getName());
    }

    @Test
    void testGetTodoById_InvalidId_ReturnsEmpty() {
        initializeServiceWithEmptyList();

        Optional<ToDoItem> result = toDoService.getTodoById("invalid");

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetTodoById_NonExistentId_ReturnsEmpty() {
        initializeServiceWithEmptyList();

        Optional<ToDoItem> result = toDoService.getTodoById("999");

        assertTrue(result.isEmpty());
    }

    @Test
    void testDeleteTodo_ValidId_Success() {
        initializeServiceWithEmptyList();
        toDoService.createTodo("Task to delete", "Description", LocalDate.now());

        boolean result = toDoService.deleteTodo("1");

        assertTrue(result);
        assertTrue(toDoService.getAllTodos().isEmpty());
        verify(fileManager, times(2)).saveTodos(eq(testFileName), any()); // Once for create, once for delete
    }

    @Test
    void testDeleteTodo_InvalidId_ReturnsFalse() {
        initializeServiceWithEmptyList();

        boolean result = toDoService.deleteTodo("invalid");

        assertFalse(result);
    }

    @Test
    void testDeleteTodo_NonExistentId_ReturnsFalse() {
        initializeServiceWithEmptyList();

        boolean result = toDoService.deleteTodo("999");

        assertFalse(result);
    }

    @Test
    void testUpdateTodo_ExistingItem_Success() {
        initializeServiceWithEmptyList();
        ToDoItem original = toDoService.createTodo("Original", "Description", LocalDate.now());
        original.setName("Updated Name");
        original.setStatus(TaskStatus.COMPLETED);

        boolean result = toDoService.updateTodo(original);

        assertTrue(result);
        Optional<ToDoItem> updated = toDoService.getTodoById("1");
        assertTrue(updated.isPresent());
        assertEquals("Updated Name", updated.get().getName());
        assertEquals(TaskStatus.COMPLETED, updated.get().getStatus());

        verify(fileManager, times(2)).saveTodos(eq(testFileName), any()); // Once for create, once for update
    }

    @Test
    void testUpdateTodo_NonExistentItem_ReturnsFalse() {
        initializeServiceWithEmptyList();
        ToDoItem nonExistent = new ToDoItem(999L, "Non Existent", "Description", LocalDate.now());

        boolean result = toDoService.updateTodo(nonExistent);

        assertFalse(result);
    }

    @Test
    void testGetTodosByStatus_ReturnsCorrectItems() {
        initializeServiceWithEmptyList();
        toDoService.createTodo("Task 1", "Description 1", LocalDate.now());
        toDoService.createTodo("Task 2", "Description 2", LocalDate.now());
        toDoService.createTodo("Task 3", "Description 3", LocalDate.now());

        List<ToDoItem> todos = toDoService.getTodosByStatus(TaskStatus.TODO);

        assertEquals(3, todos.size());
        assertTrue(todos.stream().allMatch(item -> item.getStatus() == TaskStatus.TODO));
    }

    @Test
    void testGetTodosByCompleted_ReturnsAllCompletedItems() {
        initializeServiceWithEmptyList();
        toDoService.createTodo("Task 1", "Description 1", LocalDate.now());
        toDoService.createTodo("Task 2", "Description 2", LocalDate.now());

        List<ToDoItem> todos = toDoService.getTodosByStatus(TaskStatus.COMPLETED);

        assertEquals(0, todos.size());
    }

    @Test
    void testDeleteAllTodos_WithTasks_ReturnsCount() {
        initializeServiceWithEmptyList();
        toDoService.createTodo("Task 1", "Description 1", LocalDate.now());
        toDoService.createTodo("Task 2", "Description 2", LocalDate.now());
        toDoService.createTodo("Task 3", "Description 3", LocalDate.now());

        int deletedCount = toDoService.deleteAllTodos();

        assertEquals(3, deletedCount);
        assertTrue(toDoService.getAllTodos().isEmpty());
        verify(fileManager, times(4)).saveTodos(eq(testFileName), any()); // 3 creates + 1 delete all
    }

    @Test
    void testDeleteAllTodos_EmptyList_ReturnsZero() {
        initializeServiceWithEmptyList();

        int deletedCount = toDoService.deleteAllTodos();

        assertEquals(0, deletedCount);
        assertTrue(toDoService.getAllTodos().isEmpty());
        verify(fileManager, never()).saveTodos(eq(testFileName), any()); // No save needed for empty list
    }
}