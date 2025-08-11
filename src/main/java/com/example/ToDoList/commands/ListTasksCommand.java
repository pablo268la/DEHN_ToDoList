package com.example.ToDoList.commands;

import com.example.ToDoList.model.ToDoItem;
import com.example.ToDoList.model.TaskStatus;
import com.example.ToDoList.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

import static com.example.ToDoList.utils.Utils.getColoredStatus;

@ShellComponent
@RequiredArgsConstructor
public class ListTasksCommand {

    private final ToDoService toDoService;


    @ShellMethod(key = "list", value = "List all tasks")
    public String listTasks(@ShellOption(defaultValue = "ALL") String filter) {
        List<ToDoItem> tasks;

        if ("ALL".equalsIgnoreCase(filter)) {
            tasks = toDoService.getAllTodos();
        } else {
            try {
                TaskStatus status = TaskStatus.valueOf(filter.toUpperCase().replace(" ", "_"));
                tasks = toDoService.getTodosByStatus(status);
            } catch (IllegalArgumentException e) {
                return "Invalid status. Use: TODO, IN_PROGRESS, COMPLETED or ALL";
            }
        }

        if (tasks.isEmpty()) {
            return "No tasks in the list.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-4s %-75s %-85s %-12s %-15s%n",
                "ID",
                "TITLE",
                "DESCRIPTION",
                "DUE DATE",
                "STATUS"));
        sb.append("─".repeat(195)).append("\n");

        for (ToDoItem task : tasks) {
            String title = task.getName().length() > 74 ? task.getName().substring(0, 71) + "..." : task.getName();
            String description = task.getDescription().length() > 84 ? task.getDescription().substring(0, 81) + "..." : task.getDescription();

            sb.append(String.format("%-4d %-75s %-85s %-12s %s%n",
                    task.getId(),
                    title,
                    description,
                    task.getDueDate().toString(),
                    getColoredStatus(task.getStatus())));
        }


        return sb.toString();
    }
}