package com.raushan.states;

import com.raushan.entities.Task;
import com.raushan.enums.TaskStatus;

public class TodoState implements TaskState {
    @Override
    public void startProgress(Task task) {
        task.setState(new InProgressState());
    }
    @Override
    public void completeTask(Task task) {
        System.out.println("Cannot complete a task that is not in progress.");
    }
    @Override
    public void reopenTask(Task task) {
        System.out.println("Task is already in TO-DO state.");
    }
    @Override
    public TaskStatus getStatus() { return TaskStatus.TODO; }
}
