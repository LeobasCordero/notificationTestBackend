package gila.challenge.notificationTest.service.Interfaces;

import gila.challenge.notificationTest.dto.NotificationDto;

public interface Notification {

    Boolean sendNotification(NotificationDto message);
}
