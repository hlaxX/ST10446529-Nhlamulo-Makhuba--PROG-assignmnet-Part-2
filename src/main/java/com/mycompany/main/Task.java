package com.mycompany.main;

public class Task {
    private static int taskCounter = 0; 
    private final String taskName;
    private final String taskDescription;
    private final String developerName;
    private final int taskDuration;
    private final String taskStatus;
    private final String taskId;

    public Task(String taskName, String taskDescription, String developerName, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerName = developerName;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskId = createTaskID();
        taskCounter++; 
    }

    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    public final String createTaskID() {
        String developerLastThree = developerName.length() >= 3 ? developerName.substring(developerName.length() - 3).toUpperCase() : "";
        return taskName.substring(0, 2).toUpperCase() + ":" + (taskCounter - 1) + ":" + developerLastThree;
    }

    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
               "Developer Details: " + developerName + "\n" +
               "Task Number: " + (taskCounter - 1) + "\n" +
               "Task Name: " + taskName + "\n" +
               "Task Description: " + taskDescription + "\n" +
               "Task ID: " + taskId + "\n" +
               "Duration: " + taskDuration + " hours\n";
    }

    public int getTaskDuration() {
        return taskDuration;
    }
}

