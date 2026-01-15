package com.raushan.entities;

import java.time.LocalDateTime;

abstract class Content {

    protected final String id;
    protected final String body;
    protected final User author;
    protected final LocalDateTime creationTime;

    protected Content(String id, String body, User author) {
        this.id = id;
        this.body = body;
        this.author = author;
        this.creationTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
