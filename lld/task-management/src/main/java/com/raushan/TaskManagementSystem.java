package com.raushan;

import com.raushan.entities.Project;
import com.raushan.entities.Task;
import com.raushan.entities.User;
import com.raushan.enums.TaskPriority;
import com.raushan.enums.TaskStatus;
import com.raushan.observer.ActivityLogger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class TaskManagementSystem {
    private static TaskManagementSystem instance;
    private final Map<String, User> users;
    private final Map<String, Task> tasks;
    private final Map<String, Project> taskLists;

    private TaskManagementSystem() {
        users = new ConcurrentHashMap<>();
        tasks = new ConcurrentHashMap<>();
        taskLists = new ConcurrentHashMap<>();
    }

    public static synchronized TaskManagementSystem getInstance() {
        if (instance == null) {
            instance = new TaskManagementSystem();
        }
        return instance;
    }

    public User createUser(String name, String email) {
        User user = new User(name, email);
        users.put(user.getId(), user);
        return user;
    }

    public Project createTaskList(String listName) {
        Project taskList = new Project(listName);
        taskLists.put(taskList.getId(), taskList);
        return taskList;
    }

    public Task createTask(String title, String description, LocalDate dueDate,
                           TaskPriority priority, String createdByUserId) {
        User createdBy = users.get(createdByUserId);
        if (createdBy == null)
            throw new IllegalArgumentException("User not found.");

        Task task = new Task.TaskBuilder(title)
                .description(description)
                .dueDate(dueDate)
                .priority(priority)
                .createdBy(createdBy)
                .build();

        task.addObserver(new ActivityLogger());

        tasks.put(task.getId(), task);
        return task;
    }

    public List<Task> listTasksByUser(String userId) {
        User user = users.get(userId);
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getAssignee().equals(user)) {
                result.add(task);
            }
        }
        return result;
    }

    public List<Task> listTasksByStatus(TaskStatus status) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getStatus() == status) {
                result.add(task);
            }
        }
        return result;
    }

    public void deleteTask(String taskId) {
        tasks.remove(taskId);
    }

    public List<Task> searchTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getTitle().contains(keyword) || task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
//        sortingStrategy.sort(matchingTasks);
        return matchingTasks;
    }
}