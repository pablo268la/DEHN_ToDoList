package com.example.ToDoList.commands;

import com.example.ToDoList.model.ToDoItem;
import com.example.ToDoList.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class FindTaskCommand {

    private final ToDoService toDoService;

    @ShellMethod(key = "find", value = "Find a task by ID")
    public String findTask(@ShellOption String id) {
        Optional<ToDoItem> task = toDoService.getTodoById(id);

        return task
                .map(toDoItem -> String.format("Task found:\n%s", toDoItem))
                .orElse("No task found with that ID.");
    }
}