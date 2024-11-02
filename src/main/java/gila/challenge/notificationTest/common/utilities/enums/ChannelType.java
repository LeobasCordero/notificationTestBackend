package gila.challenge.notificationTest.common.utilities.enums;

import gila.challenge.notificationTest.service.Interfaces.Notification;
import gila.challenge.notificationTest.common.utilities.factories.NotificationFactory;

import java.util.Arrays;
import java.util.function.Function;

public enum ChannelType {
    SMS("SMS", NotificationFactory::getSmsService),
    EMAIL("EMAIL", NotificationFactory::getEmailService),
    PUSH_NOTIFICATION("PUSH_NOTIFICATION", NotificationFactory::getPushNotificationService);

    private final String value;
    private final Function<NotificationFactory, Notification> serviceProvider;

    ChannelType(String value, Function<NotificationFactory, Notification> serviceProvider) {
        this.value = value;
        this.serviceProvider = serviceProvider;
    }

    public String getValue() {
        return value;
    }

    public Notification getService(NotificationFactory factory) {
        return serviceProvider.apply(factory);
    }

    public static ChannelType fromValue(String channel) {
        return Arrays.stream(ChannelType.values())
                .filter(notificacion -> notificacion.value.equals(channel))
                .findFirst()
                .orElse(null);
    }
}
