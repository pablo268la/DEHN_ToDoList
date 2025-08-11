package com.example.ToDoList.commands;

import com.example.ToDoList.model.ToDoItem;
import com.example.ToDoList.service.ToDoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class CreateTaskCommand {

    private final ToDoService toDoService;

    @ShellMethod(key = "create", value = "Create a new task")
    public String createTask(
            @ShellOption String name,
            @ShellOption String description,
            @ShellOption String dueDate) {

        try {
            LocalDate date = LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            ToDoItem newTask = toDoService.createTodo(name, description, date);
            return String.format("Task created successfully:\n%s", newTask.toString());
        } catch (DateTimeParseException e) {
            log.warn("Invalid date format: {}", dueDate);
            return "Invalid date format. Use: yyyy-MM-dd (example: 2025-12-31)";
        }
    }
}