package com.raushan.states;

import com.raushan.entities.Task;
import com.raushan.enums.TaskStatus;

class DoneState implements TaskState {
    @Override
    public void startProgress(Task task) {
        System.out.println("Cannot start a completed task. Reopen it first.");
    }
    @Override
    public void completeTask(Task task) {
        System.out.println("Task is already done.");
    }
    @Override
    public void reopenTask(Task task) {
        task.setState(new TodoState());
    }
    @Override
    public TaskStatus getStatus() { return TaskStatus.DONE; }
}
