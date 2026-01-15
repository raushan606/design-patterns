package com.raushan.entities;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private final String id;
    private final String name;
    private final AtomicInteger reputation;

    public User(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.reputation = new AtomicInteger(0);
    }

    public void updateReputation(int delta) {
        reputation.addAndGet(delta);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getReputation() {
        return reputation.get();
    }
}
