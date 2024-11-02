package gila.challenge.notificationTest.service.Interfaces;

import gila.challenge.notificationTest.dto.NotificationDto;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface Notification {

    @Async
    CompletableFuture<Map<String, Object>> sendNotification(NotificationDto message);

}
