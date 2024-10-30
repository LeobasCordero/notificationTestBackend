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
public class EmailService implements Notification {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final MessageService messageService;

    @Override
    @Async
    public void sendNotification(NotificationDto notificationDto) {
        logger.info("EmailService.sendNotification starts");
        boolean messageSent = Boolean.TRUE;

        try{
            logger.info("EmailService.sendNotification: sending notification by channel E-Mail");
            messageSent = Math.random() > 0.5;
        }catch (Exception e){
            messageSent = Boolean.FALSE;
            logger.error("EmailService.sendNotification error: {}", e.getMessage());
        }finally {
            logger.info("EmailService.sendNotification notification status: {}", messageSent);
            var status = messageSent ? NotificationStatus.SENT.name() : NotificationStatus.FAILED.name();
            logMessage(notificationDto, ChannelType.EMAIL.getValue(), status);
        }

    }

    @Override
    public void logMessage(NotificationDto message, String channel, String status) {
        messageService.saveMessage(message, channel, status);
    }
}
