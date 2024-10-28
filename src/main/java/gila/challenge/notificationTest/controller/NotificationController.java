package gila.challenge.notificationTest.controller;

import gila.challenge.notificationTest.api.NotificationApi;
import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController implements NotificationApi {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;


    @Override
    public boolean sendNotification(NotificationDto messageDto) {
        logger.info("NotificationController.sendNotification starts");
        return notificationService.send(messageDto);
    }
}
