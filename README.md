# ToDo List Application

A command-line application for task management developed with Spring Boot and Spring Shell.

## Description

This application allows you to manage a ToDo List through an interactive command-line interface. Tasks are stored in
JSON format and include information such as title, description, due date, and status.

## Requirements

- **GraalVM 21** (required for native compilation only)

## Building and Running the Executable

### 1. Create a Native Executable

To create a native executable, you have to use GraalVM:

```bash

# Compile to native executable
./mvnw -Pnative native:compile

# Run the native executable
./target/ToDoList
```

**Note:** Native compilation requires GraalVM to be installed and properly configured on your system.

## Available Commands

Once the application is running, you'll see an interactive prompt where you can use the following commands:

### 1. Create a Task

```bash
create --name "Task title" --description "Detailed description" --dueDate "2025-12-31"
```

### 2. List Tasks

```bash
# List all tasks
list

# List tasks by status
list --filter TODO
list --filter IN_PROGRESS
list --filter COMPLETED
```

### 3. Find a Task by ID

```bash
find --id "task-id"
```

### 4. Update a Task

```bash
update --id "task-id" --name "New title" --description "New description" --dueDate "2025-09-01" --status "IN_PROGRESS"
```

**Valid statuses:** `TODO`, `IN_PROGRESS`, `COMPLETED`

### 5. Delete Tasks

```bash
# Delete a specific task
delete --id "task-id"

# Delete all tasks
delete --all
```

### 6. Configure Data File

```bash
# Change the file where tasks are saved
set-filename --fileName "my-file.json"

# Change file and delete previous tasks
set-filename --fileName "new-file.json" --blank
```

## Data File

By default, tasks are saved in the `tasks.json` file in the project's root directory. You can change this using the
`set-filename` command.