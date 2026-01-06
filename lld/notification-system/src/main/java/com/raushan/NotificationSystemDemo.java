package com.raushan;


import com.raushan.entities.Notification;
import com.raushan.entities.Recipient;
import com.raushan.enums.NotificationType;

import java.util.Optional;

public class NotificationSystemDemo {
    public static void main(String[] args) throws InterruptedException {
        // 1. Setup the notification service
        NotificationService notificationService = new NotificationService(10);

        // 2. Define recipients
        Recipient recipient1 = new Recipient("user123", Optional.of("john.doe@example.com"), null, Optional.of("pushToken123"));
        Recipient recipient2 = new Recipient("user456", null, Optional.of("+15551234567"), null);

        // 3. Send various notifications using the Facade (NotificationService)

        // Scenario 1: Send a welcome email
        Notification welcomeEmail = new Notification.Builder(recipient1, NotificationType.EMAIL)
                .subject("Welcome!")
                .message("Welcome to notification system")
                .build();
        notificationService.sendNotification(welcomeEmail);

        // Scenario 2: Send a direct push notification
        Notification pushNotification = new Notification.Builder(recipient1, NotificationType.PUSH)
                .subject("New Message")
                .message("You have a new message from Jane.")
                .build();
        notificationService.sendNotification(pushNotification);

        // Scenario 3: Send order confirmation SMS
        Notification orderSms = new Notification.Builder(recipient2, NotificationType.SMS)
                .message("Your order for Digital Clock is confirmed")
                .build();
        notificationService.sendNotification(orderSms);

        // Wait for a moment to allow the queue processor to work
        Thread.sleep(1000);

        // 4. Shutdown the system
        System.out.println("\nShutting down the notification system...");
        notificationService.shutdown();
        System.out.println("System shut down successfully.");
    }
}