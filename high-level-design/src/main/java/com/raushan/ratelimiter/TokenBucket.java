package com.raushan.ratelimiter;

import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucket implements RateLimiter {

    private final int capacity;
    private final AtomicInteger token;
    private final long refillInterval;
    private long lastRefillTimestamp;
    private static final int DEFAULT_CAPACITY = 2;
    private static final long DEFAULT_REFILL_INTERVAL = 100000; // 1 second

    public TokenBucket() {
        this.capacity = DEFAULT_CAPACITY;
        this.token = new AtomicInteger(capacity);
        this.refillInterval = DEFAULT_REFILL_INTERVAL;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    @Override
    public boolean allowRequest(String key) {
        long currentTime = System.currentTimeMillis();

        // Refill tokens if the refill interval has passed
        long tokensToAdd = ((currentTime - lastRefillTimestamp) / refillInterval) * capacity;

        if (tokensToAdd > 0) {
            int newTokenCount = Math.min(capacity, token.addAndGet((int) tokensToAdd));
            token.set(newTokenCount);
            lastRefillTimestamp = currentTime;
        }

        // Check if there are tokens available
        if (token.get() > 0) {
            // Consume a token
            token.decrementAndGet();
            return true; // Request is allowed
        }
        return false; // Request is denied
    }
}
