package com.raushan.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Project {
    private final String id;
    private final String name;
    private final List<Task> tasks;

    public Project(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.tasks = new CopyOnWriteArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    // Getters...
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void display() {
        System.out.println("--- Task List: " + name + " ---");
        for (Task task : tasks) {
            task.display("");
        }
        System.out.println("-----------------------------------");
    }
}
