package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private SmsService smsService;

    @InjectMocks
    private EmailService emailService;

    @InjectMocks
    private PushNotificationService pushNotificationService;

    private NotificationDto notificationDto;
    private Message message;

    @BeforeEach
    void setUp() {
        notificationDto = NotificationDto.builder()
                .userId(1)
                .categoryId(1)
                .channelId(1)
                .content("Test message")
                .build();

        message = Message.builder()
                .id(1)
                .userId(1)
                .categoryId(1)
                .channelId(1)
                .content("Test message")
                .sentAt(LocalDateTime.now())
                .build();
    }

    @Nested
    @DisplayName("SMS Service Tests")
    class SmsServiceTests {
        @Test
        @DisplayName("Should successfully send SMS notification")
        void shouldSendSmsNotification() {
            // Arrange
            when(messageService.saveMessage(any(NotificationDto.class))).thenReturn(message);

            // Act
            Boolean result = smsService.sendNotification(notificationDto);

            // Assert
            assertThat(result).isTrue();
            verify(messageService, times(1)).saveMessage(notificationDto);
        }

        @Test
        @DisplayName("Should handle exception when saving SMS message fails")
        void shouldHandleExceptionWhenSavingSmsFails() {
            // Arrange
            when(messageService.saveMessage(any(NotificationDto.class)))
                    .thenThrow(new RuntimeException("Database error"));

            // Act & Assert
            assertThrows(RuntimeException.class, () ->
                    smsService.sendNotification(notificationDto));
            verify(messageService, times(1)).saveMessage(notificationDto);
        }
    }

    @Nested
    @DisplayName("Email Service Tests")
    class EmailServiceTests {
        @Test
        @DisplayName("Should successfully send email notification")
        void shouldSendEmailNotification() {
            // Arrange
            when(messageService.saveMessage(any(NotificationDto.class))).thenReturn(message);

            // Act
            Boolean result = emailService.sendNotification(notificationDto);

            // Assert
            assertThat(result).isTrue();
            verify(messageService, times(1)).saveMessage(notificationDto);
        }

        @Test
        @DisplayName("Should handle exception when saving email message fails")
        void shouldHandleExceptionWhenSavingEmailFails() {
            // Arrange
            when(messageService.saveMessage(any(NotificationDto.class)))
                    .thenThrow(new RuntimeException("Database error"));

            // Act & Assert
            assertThrows(RuntimeException.class, () ->
                    emailService.sendNotification(notificationDto));
            verify(messageService, times(1)).saveMessage(notificationDto);
        }
    }

    @Nested
    @DisplayName("Push Notification Service Tests")
    class PushNotificationServiceTests {
        @Test
        @DisplayName("Should successfully send push notification")
        void shouldSendPushNotification() {
            // Arrange
            when(messageService.saveMessage(any(NotificationDto.class))).thenReturn(message);

            // Act
            Boolean result = pushNotificationService.sendNotification(notificationDto);

            // Assert
            assertThat(result).isTrue();
            verify(messageService, times(1)).saveMessage(notificationDto);
        }

        @Test
        @DisplayName("Should handle exception when saving push notification fails")
        void shouldHandleExceptionWhenSavingPushFails() {
            // Arrange
            when(messageService.saveMessage(any(NotificationDto.class)))
                    .thenThrow(new RuntimeException("Database error"));

            // Act & Assert
            assertThrows(RuntimeException.class, () ->
                    pushNotificationService.sendNotification(notificationDto));
            verify(messageService, times(1)).saveMessage(notificationDto);
        }
    }
}