package com.example.ToDoList.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JacksonConfigTest {

    @Test
    void testObjectMapperBean() {
        JacksonConfig config = new JacksonConfig();
        ObjectMapper mapper = config.objectMapper();

        assertNotNull(mapper);
    }
}