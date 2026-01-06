package com.raushan;

import com.raushan.decorator.RetryableGatewayDecorator;
import com.raushan.entities.Notification;
import com.raushan.factory.NotificationFactory;
import com.raushan.strategy.NotificationGateway;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationService {
    private final ExecutorService executorService;

    public NotificationService(int threadPoolSize) {
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void sendNotification(Notification notification) {
        executorService.submit(() -> {

            NotificationGateway gateway = new RetryableGatewayDecorator(NotificationFactory.createGateway(notification.getType()),
                    3, 1000);
            try {
                gateway.send(notification);
            } catch (Exception e) {
                System.out.println("Failed to send notification after retries: " + e.getMessage());
            }

        });
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
