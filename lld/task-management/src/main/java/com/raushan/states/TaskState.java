package com.raushan.states;

import com.raushan.entities.Task;
import com.raushan.enums.TaskStatus;

public interface TaskState {
    void startProgress(Task task);
    void completeTask(Task task);
    void reopenTask(Task task);
    TaskStatus getStatus();
}
