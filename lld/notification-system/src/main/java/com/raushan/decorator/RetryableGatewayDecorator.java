package com.raushan.decorator;

import com.raushan.entities.Notification;
import com.raushan.strategy.NotificationGateway;

public class RetryableGatewayDecorator implements NotificationGateway {
    private final NotificationGateway gateway;
    private final int maxRetries;
    private final long retryDelayMillis;

    public RetryableGatewayDecorator(NotificationGateway gateway, int maxRetries, long retryDelayMillis) {
        this.gateway = gateway;
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    @Override
    public void send(Notification notification) throws Exception {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                gateway.send(notification);
                return; // Success, exit the method
            } catch (Exception e) {
                attempt++;
                if (attempt >= maxRetries) {
                    System.out.println("All retry attempts failed.");
                    throw e; // Rethrow the exception after max retries
                }
                System.out.println("Attempt " + attempt + " failed. Retrying in " + retryDelayMillis + " ms...");
                Thread.sleep(retryDelayMillis);
            }
        }
    }
}
