package com.raushan.locker;

import java.time.LocalDateTime;
import java.util.UUID;

public class AccessToken {
    private final String token;
    private final LocalDateTime expiryTime;
    private final Compartment compartment;

    public AccessToken(Compartment compartment) {
        this.compartment = compartment;
        this.token = generateToken();
        this.expiryTime = LocalDateTime.now().plusDays(7); // Token valid for 7Days
    }

    private String generateToken() {
        return "LOCKER-" + UUID.randomUUID();
    }

    public String getToken() {
        return token;
    }

    public Compartment getCompartment() {
        return compartment;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryTime);
    }

    public boolean isValid(String token) {
        return !token.isBlank() && this.token.equals(token) && !isExpired();
    }
}
