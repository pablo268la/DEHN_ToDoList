package com.example.ToDoList.service;

import com.example.ToDoList.model.ToDoItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileManagerTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private FileManager fileManager;

    @TempDir
    Path tempDir;

    private String testFilename;
    private List<ToDoItem> testTodos;

    @BeforeEach
    void setUp() {
        testFilename = tempDir.resolve("test-todos.json").toString();
        testTodos = List.of(
                new ToDoItem(1L, "Task 1", "Description 1", LocalDate.now()),
                new ToDoItem(2L, "Task 2", "Description 2", LocalDate.now().plusDays(1))
        );
    }

    @Test
    void testLoadTodos_FileExists_Success() throws IOException {
        File testFile = new File(testFilename);
        assertTrue(testFile.createNewFile());

        when(objectMapper.readValue(eq(testFile), any(TypeReference.class)))
                .thenReturn(testTodos);

        List<ToDoItem> result = fileManager.loadTodos(testFilename);

        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getName());
        assertEquals("Task 2", result.get(1).getName());
        verify(objectMapper).readValue(eq(testFile), any(TypeReference.class));
    }

    @Test
    void testLoadTodos_FileDoesNotExist_ReturnsEmptyList() {
        List<ToDoItem> result = fileManager.loadTodos("non-existent-file.json");

        assertTrue(result.isEmpty());
        verifyNoInteractions(objectMapper);
    }

    @Test
    void testLoadTodos_IOException_ReturnsEmptyList() throws IOException {
        File testFile = new File(testFilename);
        assertTrue(testFile.createNewFile());

        when(objectMapper.readValue(eq(testFile), any(TypeReference.class)))
                .thenThrow(new IOException("Test exception"));

        List<ToDoItem> result = fileManager.loadTodos(testFilename);

        assertTrue(result.isEmpty());
        verify(objectMapper).readValue(eq(testFile), any(TypeReference.class));
    }

    @Test
    void testSaveTodos_Success() throws IOException {
        boolean result = fileManager.saveTodos(testFilename, testTodos);

        assertTrue(result);
        verify(objectMapper).writeValue(eq(new File(testFilename)), eq(testTodos));
    }

    @Test
    void testSaveTodos_IOException_ReturnsFalse() throws IOException {
        doThrow(new IOException("Test exception"))
                .when(objectMapper).writeValue(any(File.class), any(List.class));

        boolean result = fileManager.saveTodos(testFilename, testTodos);

        assertFalse(result);
        verify(objectMapper).writeValue(eq(new File(testFilename)), eq(testTodos));
    }

    @Test
    void testFileExists_FileExists_ReturnsTrue() throws IOException {
        File testFile = new File(testFilename);
        assertTrue(testFile.createNewFile());

        boolean result = fileManager.fileExists(testFilename);

        assertTrue(result);
    }

    @Test
    void testFileExists_FileDoesNotExist_ReturnsFalse() {
        boolean result = fileManager.fileExists("non-existent-file.json");

        assertFalse(result);
    }
}