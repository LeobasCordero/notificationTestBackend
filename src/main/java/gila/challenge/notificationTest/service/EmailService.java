package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.common.utilities.enums.ChannelType;
import gila.challenge.notificationTest.common.utilities.utilities.Utils;
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

    private final MessageService messageService;

    @Override
    public CompletableFuture<Map<String, Object>> sendNotification(NotificationDto notificationDto) {
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
        }

        var result = Utils.createResultNotification(ChannelType.EMAIL.name(), notificationDto, messageSent);

        return CompletableFuture.completedFuture(result);
    }

}
