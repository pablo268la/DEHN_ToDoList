package com.example.ToDoList.commands;

import com.example.ToDoList.config.FileConfig;
import com.example.ToDoList.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class FileCommand {

    private final FileConfig fileConfig;

    private final ToDoService toDoService;

    @ShellMethod(key = "set-filename", value = "Set the file name for storing tasks")
    public String setFileName(
            @ShellOption(value = {"--fileName"}, defaultValue = ShellOption.NULL, help = "Name for the new file") String fileName,
            @ShellOption(value = {"--blank"}, defaultValue = "false", help = "Delete all previous tasks") boolean blank) {
        if (fileName == null || fileName.trim().isEmpty() ) {
            return "Error: File name cannot be null or empty.";
        }
        fileConfig.setFileName(fileName);
        toDoService.init();

        if (blank) {
            toDoService.deleteAllTodos();
        }
        return "File name set to: " + fileName;
    }
}