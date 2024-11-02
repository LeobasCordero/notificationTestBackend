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
public class SmsService implements Notification {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    private final MessageService messageService;

    @Override
    public CompletableFuture<Map<String, Object>> sendNotification(NotificationDto notificationDto) {
        logger.info("SmsService.sendNotification starts");
        boolean messageSent = Boolean.TRUE;

        try{
            logger.info("SmsService.sendNotification: sending notification by channel SMS");
            messageSent = Math.random() > 0.5;
        }catch (Exception e){
            logger.error("SmsService.sendNotification error: {}", e.getMessage());
            messageSent = Boolean.FALSE;
        }finally {
            logger.info("SmsService.sendNotification notification status: {}", messageSent);
        }

        var result = Utils.createResultNotification(ChannelType.SMS.name(), notificationDto, messageSent);

        return CompletableFuture.completedFuture(result);
    }

}
