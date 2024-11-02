package gila.challenge.notificationTest.common.utilities.factories;

import gila.challenge.notificationTest.service.EmailService;
import gila.challenge.notificationTest.service.Interfaces.Notification;
import gila.challenge.notificationTest.service.PushNotificationService;
import gila.challenge.notificationTest.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationFactory {

    private final SmsService smsService;
    private final EmailService emailService;
    private final PushNotificationService pushNotificationService;


    public Notification getSmsService() {
        return smsService;
    }

    public Notification getEmailService() {
        return emailService;
    }

    public Notification getPushNotificationService() {
        return pushNotificationService;
    }
}
