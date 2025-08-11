package com.example.ToDoList.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class FileConfig {

    private String fileName = "tasks.json";
}