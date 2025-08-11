package com.example.ToDoList.service;

import com.example.ToDoList.model.ToDoItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileManager {

    private final ObjectMapper objectMapper;

    public List<ToDoItem> loadTodos(String filename) {
        try {
            File file = new File(filename);
            if (file.exists()) {
                List<ToDoItem> todoItems = objectMapper.readValue(file, new TypeReference<List<ToDoItem>>() {});
                log.info("Loaded {} tasks from {}", todoItems.size(), filename);
                return todoItems;
            } else {
                log.info("File {} does not exist, starting with empty task list", filename);
                return new ArrayList<>();
            }
        } catch (IOException e) {
            log.error("Error loading tasks from {}: {}", filename, e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean saveTodos(String filename, List<ToDoItem> todoItems) {
        try {
            objectMapper.writeValue(new File(filename), todoItems);
            log.debug("Tasks saved to {}", filename);
            return true;
        } catch (IOException e) {
            log.error("Error saving tasks to {}: {}", filename, e.getMessage());
            return false;
        }
    }

    public boolean fileExists(String filename) {
        return new File(filename).exists();
    }
}