package gila.challenge.notificationTest.utilities.factories;

import gila.challenge.notificationTest.service.EmailService;
import gila.challenge.notificationTest.service.Interfaces.Notification;
import gila.challenge.notificationTest.service.PushNotificationService;
import gila.challenge.notificationTest.service.SmsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class NotificationFactoryTest {

    @Mock
    private SmsService smsService;

    @Mock
    private EmailService emailService;

    @Mock
    private PushNotificationService pushNotificationService;

    @InjectMocks
    private NotificationFactory notificationFactory;

    @Test
    @DisplayName("Should return SMS service for SMS type")
    void shouldReturnSmsService() {
        Notification service = notificationFactory.getSmsService();

        assertThat(service).isInstanceOf(SmsService.class);
    }

    @Test
    @DisplayName("Should return Email service for Email type")
    void shouldReturnEmailService() {
        Notification service = notificationFactory.getEmailService();

        assertThat(service).isInstanceOf(EmailService.class);
    }

    @Test
    @DisplayName("Should return Push service for Push type")
    void shouldReturnPushService() {
        Notification service = notificationFactory.getPushNotificationService();

        assertThat(service).isInstanceOf(PushNotificationService.class);
    }

}