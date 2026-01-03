package com.raushan;

import com.raushan.entities.Project;
import com.raushan.entities.Task;
import com.raushan.entities.User;
import com.raushan.enums.TaskPriority;
import com.raushan.enums.TaskStatus;

import java.util.*;
import java.time.LocalDate;
import java.util.concurrent.*;

public class TaskManagementSystemDemo {
    public static void main(String[] args) {
        TaskManagementSystem taskManagementSystem = TaskManagementSystem.getInstance();

        // Create users
        User user1 = taskManagementSystem.createUser("John Doe", "john@example.com");
        User user2 = taskManagementSystem.createUser("Jane Smith", "jane@example.com");

        // Create Project
        Project taskList1 = taskManagementSystem.createTaskList("Enhancements");
        Project taskList2 = taskManagementSystem.createTaskList("Bug Fix");

        // Create tasks
        Task task1 = taskManagementSystem.createTask("Enhancement Task", "Launch New Feature",
                LocalDate.now().plusDays(2), TaskPriority.LOW, user1.getId());
        Task subtask1 = taskManagementSystem.createTask( "Enhancement sub task", "Design UI/UX",
                LocalDate.now().plusDays(1), TaskPriority.MEDIUM, user1.getId());
        Task task2 = taskManagementSystem.createTask("Bug Fix Task", "Fix API Bug",
                LocalDate.now().plusDays(3), TaskPriority.HIGH, user2.getId());

        task1.addSubtask(subtask1);

        taskList1.addTask(task1);
        taskList2.addTask(task2);

        taskList1.display();

        // Update task status
        subtask1.startProgress();

        // Assign task
        subtask1.setAssignee(user2);

        taskList1.display();

        // Search tasks
        List<Task> searchResults = taskManagementSystem.searchTasks("Task");
        System.out.println("\nTasks with keyword Task:");
        for (Task task : searchResults) {
            System.out.println(task.getTitle());
        }

        // Filter tasks by status
        List<Task> filteredTasks = taskManagementSystem.listTasksByStatus(TaskStatus.TODO);
        System.out.println("\nTODO Tasks:");
        for (Task task : filteredTasks) {
            System.out.println(task.getTitle());
        }

        // Mark a task as done
        subtask1.completeTask();

        // Get tasks assigned to a user
        List<Task> userTaskList = taskManagementSystem.listTasksByUser(user2.getId());
        System.out.println("\nTask for " + user2.getName() + ":");
        for (Task task : userTaskList) {
            System.out.println(task.getTitle());
        }

        taskList1.display();

        // Delete a task
        taskManagementSystem.deleteTask(task2.getId());
    }
}
