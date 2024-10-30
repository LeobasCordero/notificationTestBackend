package gila.challenge.notificationTest.service.Interfaces;

import gila.challenge.notificationTest.dto.NotificationDto;

public interface Notification {

    void sendNotification(NotificationDto message);

    void logMessage(NotificationDto message, String channel, String status);
}
