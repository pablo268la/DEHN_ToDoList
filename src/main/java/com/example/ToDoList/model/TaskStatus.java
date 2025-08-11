package com.example.ToDoList.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStatus {
    TODO("To Do"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");

    private final String displayName;

    @Override
    public String toString() {
        return displayName;
    }
}