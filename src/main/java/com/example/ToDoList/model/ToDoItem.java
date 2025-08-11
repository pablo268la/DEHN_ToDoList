package com.example.ToDoList.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.example.ToDoList.utils.Utils.getColoredStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoItem {
    private Long id;
    private String name;
    private TaskStatus status;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;


    public ToDoItem(Long id, String name, String description, LocalDate dueDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.status = TaskStatus.TODO;
    }

    @Override
    public String toString() {
        return String.format("[%s] #%d - %s - %s (Due: %s)",
                getColoredStatus(status), id, name, description, dueDate);
    }


}