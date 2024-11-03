package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.common.utilities.builders.Builders;
import gila.challenge.notificationTest.dto.NotificationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mockStatic;

class SmsServiceTest {

    @InjectMocks
    private SmsService smsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendNotification_Success() throws Exception {
        NotificationDto notificationDto = NotificationDto.builder()
                .userName("John Doe")
                .categoryName("FINANCE")
                .channelName("SMS")
                .content("This is a test sms notification")
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .userId(1)
                .categoryId(2)
                .channelId(2)
                .build();

        Map<String, Object> resultMapMock = new HashMap<>();
        resultMapMock.put("ResultNotification", Boolean.TRUE);
        resultMapMock.put("ChannelType", "SMS");

        try (MockedStatic<Builders> buildersMock = mockStatic(Builders.class)) {
            buildersMock.when(() -> Builders.createResultNotification(anyString(), any(), anyBoolean()))
                    .thenReturn(resultMapMock);

            CompletableFuture<Map<String, Object>> result = smsService.sendNotification(notificationDto);
            Map<String, Object> resultMap = result.get();

            assertNotNull(resultMap);
            assertEquals("SMS", resultMap.get("ChannelType"));
            assertTrue((Boolean) resultMap.get("ResultNotification"));
        }
    }

    @Test
    void testSendNotification_Failure() throws Exception {
        NotificationDto notificationDto = NotificationDto.builder()
                .userName("John Doe")
                .categoryName("FINANCE")
                .channelName("SMS")
                .content("This is a test notification")
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .userId(1)
                .categoryId(2)
                .channelId(2)
                .build();

        Map<String, Object> resultMapMock = new HashMap<>();
        resultMapMock.put("ResultNotification", Boolean.FALSE);
        resultMapMock.put("ChannelType", "SMS");

        try (MockedStatic<Builders> buildersMock = mockStatic(Builders.class)) {
            buildersMock.when(() -> Builders.createResultNotification(anyString(), any(), anyBoolean()))
                    .thenReturn(resultMapMock);

            CompletableFuture<Map<String, Object>> result = smsService.sendNotification(notificationDto);
            Map<String, Object> resultMap = result.get();

            assertNotNull(resultMap);
            assertEquals("SMS", resultMap.get("ChannelType"));
            assertFalse((Boolean) resultMap.get("ResultNotification"));
        }
    }
}