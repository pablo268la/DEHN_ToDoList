package com.example.ToDoList.commands;

import com.example.ToDoList.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class DeleteTaskCommand {

    private final ToDoService toDoService;

    @ShellMethod(key = "delete", value = "Delete a task by ID or delete all tasks")
    public String deleteTask(
            @ShellOption(value = {"--id"}, defaultValue = ShellOption.NULL, help = "ID of the task to delete") String id,
            @ShellOption(value = {"--all"}, defaultValue = "false", help = "Delete all tasks") boolean all) {

        if (all && id != null) {
            return "Error: Cannot use both --id and --all options together. Choose one.";
        }

        if (all) {
            int deletedCount = toDoService.deleteAllTodos();
            if (deletedCount > 0) {
                return String.format("Successfully deleted all %d tasks.", deletedCount);
            } else {
                return "No tasks to delete.";
            }
        } else if (id != null) {
            boolean deleted = toDoService.deleteTodo(id);
            if (deleted) {
                return "Task deleted successfully.";
            } else {
                return "No task found with that ID.";
            }
        } else {
            return "Error: You must specify either --id <task_id> or --all to delete tasks.";
        }
    }
}