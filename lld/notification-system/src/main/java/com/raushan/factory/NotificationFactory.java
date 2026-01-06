package com.raushan.factory;

import com.raushan.enums.NotificationType;
import com.raushan.strategy.EmailGateway;
import com.raushan.strategy.NotificationGateway;
import com.raushan.strategy.PushGateway;
import com.raushan.strategy.SmsGateway;

import java.util.HashMap;
import java.util.Map;

public class NotificationFactory {
    private static final Map<NotificationType, NotificationGateway> gatewayMapping = new HashMap<>();

    public static NotificationGateway createGateway(NotificationType type) {
        if (gatewayMapping.containsKey(type)) {
            return gatewayMapping.get(type);
        }

        NotificationGateway gateway = null;

        switch (type) {
            case SMS -> gateway = new SmsGateway();
            case EMAIL -> gateway = new EmailGateway();
            case PUSH -> gateway = new PushGateway();
        }
        gatewayMapping.put(type, gateway);
        return gateway;
    }
}
