package com.example.ToDoList.utils;

import com.example.ToDoList.model.TaskStatus;

public class Utils {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";

    public static String getColoredStatus(TaskStatus status) {
        return switch (status) {
            case TODO -> RED + status + RESET;
            case IN_PROGRESS -> YELLOW + status + RESET;
            case COMPLETED -> GREEN + status + RESET;
        };
    }

}