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
public class SmsService implements Notification {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    private final MessageService messageService;

    @Override
    public Boolean sendNotification(NotificationDto notificationDto) {
        logger.info("SmsService.sendNotification starts");
        boolean messageSent = Boolean.TRUE;

        try{
            logger.info("SmsService.sendNotification: sending notification by channel SMS");
        }catch (Exception e){
            logger.error("SmsService.sendNotification error: {}", e.getMessage());
            return Boolean.FALSE;
        }finally {
            logger.info("SmsService.sendNotification notification status: {}", messageSent);
            logMessage(notificationDto, ChannelType.PUSH_NOTIFICATION.getValue());
        }

        return messageSent;
    }

    @Override
    public void logMessage(NotificationDto message, String channel) {
        messageService.saveMessage(message, channel);
    }
}
