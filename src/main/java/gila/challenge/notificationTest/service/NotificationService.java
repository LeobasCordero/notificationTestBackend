package gila.challenge.notificationTest.service;


import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.service.Interfaces.Notification;
import gila.challenge.notificationTest.utilities.enums.ChannelType;
import gila.challenge.notificationTest.utilities.factories.NotificationFactory;
import gila.challenge.notificationTest.utilities.validators.NotificationValidations;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final NotificationFactory notificationFactory;

    public boolean send(NotificationDto notificationDto){
        logger.info("NotificationService.send starts");
        NotificationValidations.validateNotificationToStore(notificationDto);
        Notification notification = getNotificationService(notificationDto.getChannelName());
        if(!Objects.isNull(notification)){
            return notification.sendNotification(notificationDto);
        }else{
            logger.error("NotificationService.send error: Channel not supported");
            return Boolean.FALSE;
        }
    }

    private Notification getNotificationService(String categoryName) {
        logger.info("NotificationService.getNotificationService starts");
        return ChannelType.fromValue(categoryName)
                .getService(notificationFactory);
    }


}
