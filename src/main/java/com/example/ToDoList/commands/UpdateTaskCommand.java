package com.example.ToDoList.commands;

import com.example.ToDoList.model.ToDoItem;
import com.example.ToDoList.model.TaskStatus;
import com.example.ToDoList.service.ToDoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class UpdateTaskCommand {

    private final ToDoService toDoService;

    @ShellMethod(key = "update", value = "Update task title, description, due date or status")
    public String updateTask(
            @ShellOption String id,
            @ShellOption(defaultValue = "") String name,
            @ShellOption(defaultValue = "") String description,
            @ShellOption(defaultValue = "") String dueDate,
            @ShellOption(defaultValue = "") String status) {

        Optional<ToDoItem> taskOpt = toDoService.getTodoById(id);
        if (taskOpt.isEmpty()) {
            return "No task found with that ID.";
        }

        ToDoItem task = taskOpt.get();
        boolean updated = false;


        if (!name.isEmpty()) {
            task.setName(name);
            updated = true;
        }


        if (!description.isEmpty()) {
            task.setDescription(description);
            updated = true;
        }


        if (!dueDate.isEmpty()) {
            try {
                LocalDate newDate = LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                task.setDueDate(newDate);
                updated = true;
            } catch (DateTimeParseException e) {
                log.warn("Invalid date format provided: {}", dueDate);
                return "Invalid date format. Use: yyyy-MM-dd (example: 2025-12-31)";
            }
        }


        if (!status.isEmpty()) {
            try {
                TaskStatus newStatus = TaskStatus.valueOf(status.toUpperCase());
                task.setStatus(newStatus);
                updated = true;
            } catch (IllegalArgumentException e) {
                log.warn("Invalid status provided: {}", status);
                return "Invalid status. Use: TODO, IN_PROGRESS or COMPLETED";
            }
        }

        if (!updated) {
            return "No fields to update. Provide at least one: --name, --description, --due-date, or --status";
        }

        boolean saved = toDoService.updateTodo(task);
        if (saved) {
            return String.format("Task updated successfully:\n%s", task);
        } else {
            return "Error saving task updates.";
        }
    }
}