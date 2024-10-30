package com.mycompany.main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final login loginSystem = new login();
    private static final List<Task> tasks = new ArrayList<>();
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("EasyKanban");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 600);
            frame.setLayout(new BorderLayout());
            
            
            JLabel welcomeLabel = new JLabel("Welcome to EasyKanban", SwingConstants.CENTER);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            frame.add(welcomeLabel, BorderLayout.NORTH);
            
            
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            
           
            JTextField usernameField = new JTextField(20);
            JTextField passwordField = new JTextField(20);
            JTextField firstNameField = new JTextField(20);
            JTextField lastNameField = new JTextField(20);
            JButton registerButton = new JButton("Register");
            JButton loginButton = new JButton("Login");
            JButton addTaskButton = new JButton("Add Task");
            JButton showReportButton = new JButton("Show Report");
            JTextArea outputArea = new JTextArea(10, 30);
            outputArea.setEditable(false);

            
            panel.add(new JLabel("Username:"));
            panel.add(usernameField);
            panel.add(new JLabel("Password:"));
            panel.add(passwordField);
            panel.add(new JLabel("First Name:"));
            panel.add(firstNameField);
            panel.add(new JLabel("Last Name:"));
            panel.add(lastNameField);
            panel.add(registerButton);
            panel.add(loginButton);
            panel.add(addTaskButton);
            panel.add(showReportButton);
            panel.add(new JScrollPane(outputArea));

            frame.add(panel, BorderLayout.CENTER);
            frame.setVisible(true);

            
            registerButton.addActionListener(e -> {
                String username = usernameField.getText();
                String password = passwordField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                loginSystem.registerUser(username, password, firstName, lastName); 
                outputArea.append("Registration successful!\n");
            });

            loginButton.addActionListener(e -> {
                String username = usernameField.getText();
                String password = passwordField.getText();
                if (loginSystem.loginUser(username, password)) {
                    outputArea.append("Login successful!\n");
                    
                    addTaskButton.setEnabled(true);
                } else {
                    outputArea.append("Login failed!\n");
                }
            });

            addTaskButton.addActionListener(e -> {
                
                if (!loginSystem.isLoggedIn()) {
                    outputArea.append("Please log in first!\n");
                    return;
                }
                String taskName = JOptionPane.showInputDialog(frame, "Enter Task Name:");
                String taskDescription = JOptionPane.showInputDialog(frame, "Enter Task Description (max 50 characters):");
                String developerName = JOptionPane.showInputDialog(frame, "Enter Developer Name:");
                String durationStr = JOptionPane.showInputDialog(frame, "Enter Task Duration (hours):");
                int taskDuration = Integer.parseInt(durationStr);
                String[] options = {"To Do", "Doing", "Done"};
                String taskStatus = (String) JOptionPane.showInputDialog(frame, "Select Task Status:", "Task Status", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                Task newTask = new Task(taskName, taskDescription, developerName, taskDuration, taskStatus);
                if (newTask.checkTaskDescription()) {
                    tasks.add(newTask);
                    outputArea.append(newTask.printTaskDetails() + "\n");
                }
            });

            showReportButton.addActionListener(e -> {
                
                if (tasks.isEmpty()) {
                    outputArea.append("No tasks available.\n");
                } else {
                    int totalHours = tasks.stream().mapToInt(Task::getTaskDuration).sum();
                    outputArea.append("Total hours across all tasks: " + totalHours + "\n");
                }
            });
        });
    }
}

