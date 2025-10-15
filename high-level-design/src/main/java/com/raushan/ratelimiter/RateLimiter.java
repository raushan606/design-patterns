package com.raushan.ratelimiter;

public interface RateLimiter {

    // Method to check if a request is allowed based on the key (clientId, appKey, etc.)
    boolean allowRequest(String key);

}
