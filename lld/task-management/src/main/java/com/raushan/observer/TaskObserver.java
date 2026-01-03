package com.raushan.observer;

import com.raushan.entities.Task;

public interface TaskObserver {
    void update(Task task, String changeType);
}
