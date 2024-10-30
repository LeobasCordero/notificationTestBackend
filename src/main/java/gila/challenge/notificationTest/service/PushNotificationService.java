package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.service.Interfaces.Notification;
import gila.challenge.notificationTest.utilities.enums.ChannelType;
import gila.challenge.notificationTest.utilities.enums.NotificationStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PushNotificationService implements Notification {

    private static final Logger logger = LoggerFactory.getLogger(PushNotificationService.class);

    private final MessageService messageService;

    @Override
    @Async
    public void sendNotification(NotificationDto notificationDto) {
        logger.info("PushNotificationService.sendNotification starts");
        boolean messageSent = Boolean.TRUE;

        try{
            logger.info("PushNotificationService.sendNotification: sending notification by channel Push Notification");
            messageSent = Math.random() > 0.5;
        }catch (Exception e){
            messageSent = Boolean.FALSE;
            logger.info("PushNotificationService.sendNotification error: {}", e.getMessage());
        }finally {
            logger.info("PushNotificationService.sendNotification status: {}", messageSent);
            var status = messageSent ? NotificationStatus.SENT.name() : NotificationStatus.FAILED.name();
            logMessage(notificationDto, ChannelType.PUSH_NOTIFICATION.getValue(), status);
        }

    }

    @Override
    public void logMessage(NotificationDto message, String channel, String status) {
        messageService.saveMessage(message, channel, status);
    }
}
