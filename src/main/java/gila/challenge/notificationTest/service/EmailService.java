package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.service.Interfaces.Notification;
import gila.challenge.notificationTest.utilities.enums.ChannelType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements Notification {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final MessageService messageService;

    @Override
    public Boolean sendNotification(NotificationDto notificationDto) {
        logger.info("EmailService.sendNotification starts");
        boolean messageSent = Boolean.TRUE;

        try{
            logger.info("EmailService.sendNotification: sending notification by channel E-Mail");
        }catch (Exception e){
            logger.error("EmailService.sendNotification error: {}", e.getMessage());
            return Boolean.FALSE;
        }finally {
            logger.info("EmailService.sendNotification notification status: {}", messageSent);
            logMessage(notificationDto, ChannelType.EMAIL.getValue());
        }

        return messageSent;
    }

    @Override
    public void logMessage(NotificationDto message, String channel) {
        messageService.saveMessage(message, channel);
    }
}
