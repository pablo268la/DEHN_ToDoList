package com.example.ToDoList.commands;

import com.example.ToDoList.config.FileConfig;
import com.example.ToDoList.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileCommandTest {

    @Mock
    private FileConfig fileConfig;

    @Mock
    private ToDoService toDoService;

    @InjectMocks
    private FileCommand fileCommand;

    @Test
    void testSetFileNameSuccess() {
        String fileName = "new-tasks.json";

        String result = fileCommand.setFileName(fileName, false);

        assertEquals("File name set to: " + fileName, result);
        verify(fileConfig).setFileName(fileName);
        verify(toDoService).init();
    }

    @Test
    void testSetFileNameWithBlankOption() {
        String fileName = "new-tasks.json";

        String result = fileCommand.setFileName(fileName, true);

        assertEquals("File name set to: " + fileName, result);
        verify(fileConfig).setFileName(fileName);
        verify(toDoService).deleteAllTodos();
    }

    @Test
    void testSetFileNameWithNullFileName() {
        String result = fileCommand.setFileName(null, false);

        assertEquals("Error: File name cannot be null or empty.", result);
        verifyNoInteractions(fileConfig);
        verifyNoInteractions(toDoService);
    }

    @Test
    void testSetFileNameWithEmptyFileName() {
        String result = fileCommand.setFileName("", false);

        assertEquals("Error: File name cannot be null or empty.", result);
        verifyNoInteractions(fileConfig);
        verifyNoInteractions(toDoService);
    }

}