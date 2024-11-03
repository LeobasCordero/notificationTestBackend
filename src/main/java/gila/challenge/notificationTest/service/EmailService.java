package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.common.utilities.builders.Builders;
import gila.challenge.notificationTest.common.utilities.enums.ChannelType;
import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.service.Interfaces.Notification;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class EmailService implements Notification {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Override
    public CompletableFuture<Map<String, Object>> sendNotification(NotificationDto notificationDto) {
        logger.info("EmailService.sendNotification starts");
        var messageSent = Math.random() > 0.5;
        logger.info("EmailService.sendNotification sent notification: {}", messageSent);
        var result = Builders.createResultNotification(ChannelType.EMAIL.name(), notificationDto, messageSent);

        return CompletableFuture.completedFuture(result);
    }

}
