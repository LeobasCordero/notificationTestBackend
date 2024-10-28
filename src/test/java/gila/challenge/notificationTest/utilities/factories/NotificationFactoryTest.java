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
        // Act
        Notification service = notificationFactory.getSmsService();

        // Assert
        assertThat(service).isInstanceOf(SmsService.class);
    }

    @Test
    @DisplayName("Should return Email service for Email type")
    void shouldReturnEmailService() {
        // Act
        Notification service = notificationFactory.getEmailService();

        // Assert
        assertThat(service).isInstanceOf(EmailService.class);
    }

    @Test
    @DisplayName("Should return Push service for Push type")
    void shouldReturnPushService() {
        // Act
        Notification service = notificationFactory.getPushNotificationService();

        // Assert
        assertThat(service).isInstanceOf(PushNotificationService.class);
    }

}