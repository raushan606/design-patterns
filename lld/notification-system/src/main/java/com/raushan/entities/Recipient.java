package com.raushan.entities;

import java.util.Optional;

public class Recipient {
    private final String userId;
    private final Optional<String> email;
    private final Optional<String> phoneNumber;
    private final Optional<String> pushToken;

    public Recipient(String userId, Optional<String> email, Optional<String> phoneNumber, Optional<String> pushToken) {
        this.userId = userId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.pushToken = pushToken;
    }

    public String getUserId() {
        return userId;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public Optional<String> getPhoneNumber() {
        return phoneNumber;
    }

    public Optional<String> getPushToken() {
        return pushToken;
    }
}
