package com.example.ToDoList.service;

import com.example.ToDoList.config.FileConfig;
import com.example.ToDoList.model.ToDoItem;
import com.example.ToDoList.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ToDoService {

    private final FileConfig fileConfig;

    private final FileManager fileManager;

    private List<ToDoItem> todoItems = new ArrayList<>();
    private Long nextId = 1L;

    @PostConstruct
    public void init() {
        loadTodos();
        calculateNextId();
    }

    private void calculateNextId() {
        this.nextId = todoItems.stream()
                .mapToLong(ToDoItem::getId)
                .max()
                .orElse(0L) + 1L;
        log.debug("Next ID calculated: {}", nextId);
    }

    private void loadTodos() {
        todoItems = fileManager.loadTodos(fileConfig.getFileName());
    }

    private void saveTodosToFile() {
        if (!fileManager.saveTodos(fileConfig.getFileName(), todoItems)) {
            log.error("Failed to save tasks to file");
        }
    }

    public List<ToDoItem> getAllTodos() {
        return new ArrayList<>(todoItems);
    }

    public ToDoItem createTodo(String name, String description, LocalDate dueDate) {
        ToDoItem newItem = new ToDoItem(nextId++, name, description, dueDate);
        todoItems.add(newItem);
        saveTodosToFile();
        return newItem;
    }

    public Optional<ToDoItem> getTodoById(String id) {
        try {
            Long idLong = Long.parseLong(id);
            return todoItems.stream()
                    .filter(item -> item.getId().equals(idLong))
                    .findFirst();
        } catch (NumberFormatException e) {
            log.warn("Invalid ID provided: {}", id);
            return Optional.empty();
        }
    }

    public boolean deleteTodo(String id) {
        try {
            Long idLong = Long.parseLong(id);
            Optional<ToDoItem> todoOpt = todoItems.stream()
                    .filter(item -> item.getId().equals(idLong))
                    .findFirst();

            boolean removed = todoItems.removeIf(item -> item.getId().equals(idLong));
            if (removed) {
                saveTodosToFile();
                todoOpt.ifPresent(item -> log.info("Task deleted: #{} - {}", item.getId(), item.getName()));
            }
            return removed;
        } catch (NumberFormatException e) {
            log.warn("Invalid ID provided for deletion: {}", id);
            return false;
        }
    }

    public List<ToDoItem> getTodosByStatus(TaskStatus status) {
        return todoItems.stream()
                .filter(item -> item.getStatus() == status)
                .toList();
    }

    public boolean updateTodo(ToDoItem todo) {
        for (int i = 0; i < todoItems.size(); i++) {
            if (todoItems.get(i).getId().equals(todo.getId())) {
                todoItems.set(i, todo);
                saveTodosToFile();
                return true;
            }
        }
        return false;
    }

    public int deleteAllTodos() {
        int deletedCount = todoItems.size();
        if (deletedCount > 0) {
            todoItems.clear();
            saveTodosToFile();
            log.info("All {} tasks deleted", deletedCount);
        }
        return deletedCount;
    }
}