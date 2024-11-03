package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.common.utilities.factories.NotificationFactory;
import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.model.User;
import gila.challenge.notificationTest.service.Interfaces.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @Mock
    private NotificationFactory notificationFactory;

    @Mock
    private UserService userService;

    @Mock
    private Notification notification;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendNotification_Success() {
        NotificationDto notificationDto = NotificationDto.builder()
                .userName("John Doe")
                .categoryName("MOVIES")
                .channelName("PUSH_NOTIFICATION")
                .content("This is a test notification")
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .userId(1)
                .categoryId(2)
                .channelId(3)
                .build();

        Channel channel = new Channel();
        channel.setName("PUSH_NOTIFICATION");

        User user = new User();
        user.setChannels(List.of(channel));

        when(userService.getUserById(notificationDto.getUserId())).thenReturn(user);
        when(notificationFactory.getPushNotificationService()).thenReturn(notification);

        notificationService.send(notificationDto);

        verify(userService, times(1)).getUserById(notificationDto.getUserId());
        verify(notificationFactory, times(1)).getPushNotificationService();
        verify(notification, times(1)).sendNotification(notificationDto);
    }

    @Test
    void testSendNotification_ChannelNotSupported() {
        NotificationDto notificationDto = NotificationDto.builder()
                .userName("John Doe")
                .categoryName("MOVIES")
                .channelName("UNSUPPORTED_CHANNEL")
                .content("This is a test notification")
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .userId(1)
                .categoryId(2)
                .channelId(3)
                .build();

        Channel channel = new Channel();
        channel.setName("UNSUPPORTED_CHANNEL");

        User user = new User();
        user.setChannels(List.of(channel));

        when(userService.getUserById(notificationDto.getUserId())).thenReturn(user);
        when(notificationFactory.getPushNotificationService()).thenReturn(null);

        notificationService.send(notificationDto);

        verify(userService, times(1)).getUserById(notificationDto.getUserId());
    }
}
