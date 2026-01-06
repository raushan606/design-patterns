package com.raushan.strategy;

import com.raushan.entities.Notification;

public interface NotificationGateway {
    void send(Notification notification) throws Exception;
}
