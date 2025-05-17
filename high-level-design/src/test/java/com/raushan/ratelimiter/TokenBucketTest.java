package com.raushan.ratelimiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenBucketTest {

    @Test
    void allowRequest() {
        var tokenBucket = new TokenBucket();
        // Test that the first request is allowed
        assertTrue(tokenBucket.allowRequest("client1"));
        // Test that the second request is allowed
        assertTrue(tokenBucket.allowRequest("client1"));
        // Test that the third request is not allowed
        assertFalse(tokenBucket.allowRequest("client1"));

    }
}