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
            when(messageService.saveMessage(any(NotificationDto.class),anyString(), anyString())).thenReturn(message);

            smsService.sendNotification(notificationDto);

            verify(messageService, times(1)).saveMessage(notificationDto, "SMS", "SENT");
        }

        @Test
        @DisplayName("Should handle exception when saving SMS message fails")
        void shouldHandleExceptionWhenSavingSmsFails() {
            when(messageService.saveMessage(any(NotificationDto.class), anyString(), anyString()))
                    .thenThrow(new RuntimeException("Database error"));

            assertThrows(RuntimeException.class, () ->
                    smsService.sendNotification(notificationDto));
            verify(messageService, times(1)).saveMessage(notificationDto, "SMS", "FAILED");
        }
    }

    @Nested
    @DisplayName("Email Service Tests")
    class EmailServiceTests {
        @Test
        @DisplayName("Should successfully send email notification")
        void shouldSendEmailNotification() {
            when(messageService.saveMessage(any(NotificationDto.class), anyString(), anyString())).thenReturn(message);

            emailService.sendNotification(notificationDto);

            verify(messageService, times(1)).saveMessage(notificationDto, "SMS", "SENT");
        }

        @Test
        @DisplayName("Should handle exception when saving email message fails")
        void shouldHandleExceptionWhenSavingEmailFails() {
            when(messageService.saveMessage(any(NotificationDto.class), anyString(), anyString()))
                    .thenThrow(new RuntimeException("Database error"));

            assertThrows(RuntimeException.class, () ->
                    emailService.sendNotification(notificationDto));
            verify(messageService, times(1)).saveMessage(notificationDto, "SMS", "FAILED");
        }
    }

    @Nested
    @DisplayName("Push Notification Service Tests")
    class PushNotificationServiceTests {
        @Test
        @DisplayName("Should successfully send push notification")
        void shouldSendPushNotification() {
            when(messageService.saveMessage(any(NotificationDto.class), anyString(), anyString())).thenReturn(message);

            pushNotificationService.sendNotification(notificationDto);

            verify(messageService, times(1)).saveMessage(notificationDto, "SMS", "SENT");
        }

        @Test
        @DisplayName("Should handle exception when saving push notification fails")
        void shouldHandleExceptionWhenSavingPushFails() {
            when(messageService.saveMessage(any(NotificationDto.class), anyString(), anyString()))
                    .thenThrow(new RuntimeException("Database error"));

            assertThrows(RuntimeException.class, () ->
                    pushNotificationService.sendNotification(notificationDto));
            verify(messageService, times(1)).saveMessage(notificationDto, "SMS", "FAILED");
        }
    }
}