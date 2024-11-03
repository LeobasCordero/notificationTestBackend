package gila.challenge.notificationTest.aspect;

import gila.challenge.notificationTest.common.aop.NotificationAspect;
import gila.challenge.notificationTest.common.utilities.constants.NotificationConstants;
import gila.challenge.notificationTest.common.utilities.enums.NotificationStatus;
import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class NotificationAspectTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private NotificationAspect notificationAspect;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAfterSendingNotification_Success() {
        NotificationDto notificationDto = NotificationDto.builder()
                .userName("John Doe")
                .categoryName("Movies")
                .channelName("PUSH_NOTIFICATION")
                .content("This is a test notification")
                .status("SENT")
                .build();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put(NotificationConstants.RESULT, true);
        responseMap.put(NotificationConstants.NOTIFICATION, notificationDto);
        responseMap.put(NotificationConstants.CHANNEL_TYPE, "PUSH_NOTIFICATION");

        CompletableFuture<Map<String, Object>> result = CompletableFuture.completedFuture(responseMap);

        notificationAspect.afterSendingNotification(result);

        result.join();  // Ensure the CompletableFuture is completed

        verify(messageService, times(1)).saveMessage(eq(notificationDto), eq("PUSH_NOTIFICATION"), eq(NotificationStatus.SENT.name()));
    }

    @Test
    void testAfterSendingNotification_Failure() {
        NotificationDto notificationDto = NotificationDto.builder()
                .userName("John Doe")
                .categoryName("Movies")
                .channelName("PUSH_NOTIFICATION")
                .content("This is a test notification")
                .status("FAILED")
                .build();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put(NotificationConstants.RESULT, false);
        responseMap.put(NotificationConstants.NOTIFICATION, notificationDto);
        responseMap.put(NotificationConstants.CHANNEL_TYPE, "PUSH_NOTIFICATION");

        CompletableFuture<Map<String, Object>> result = CompletableFuture.completedFuture(responseMap);

        notificationAspect.afterSendingNotification(result);

        result.join();  // Ensure the CompletableFuture is completed

        verify(messageService, times(1)).saveMessage(eq(notificationDto), eq("PUSH_NOTIFICATION"), eq(NotificationStatus.FAILED.name()));
    }
}
