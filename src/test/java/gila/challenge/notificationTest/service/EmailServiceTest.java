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

class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendNotification_Success() throws Exception {
        NotificationDto notificationDto = NotificationDto.builder()
                .userName("John Doe")
                .categoryName("SPORTS")
                .channelName("EMAIL")
                .content("This is an email test notification")
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .userId(1)
                .categoryId(1)
                .channelId(1)
                .build();

        Map<String, Object> resultMapMock = new HashMap<>();
        resultMapMock.put("ResultNotification", Boolean.TRUE);
        resultMapMock.put("ChannelType", "EMAIL");

        try (MockedStatic<Builders> buildersMock = mockStatic(Builders.class)) {
            buildersMock.when(() -> Builders.createResultNotification(anyString(), any(), anyBoolean()))
                    .thenReturn(resultMapMock);

            CompletableFuture<Map<String, Object>> result = emailService.sendNotification(notificationDto);
            Map<String, Object> resultMap = result.get();

            assertNotNull(resultMap);
            assertEquals("EMAIL", resultMap.get("ChannelType"));
            assertTrue((Boolean) resultMap.get("ResultNotification"));
        }
    }

    @Test
    void testSendNotification_Failure() throws Exception {
        NotificationDto notificationDto = NotificationDto.builder()
                .userName("John Doe")
                .categoryName("SPORTS")
                .channelName("EMAIL")
                .content("This is an email test notification")
                .status("FAILED")
                .sentAt(LocalDateTime.now())
                .userId(1)
                .categoryId(2)
                .channelId(3)
                .build();

        Map<String, Object> resultMapMock = new HashMap<>();
        resultMapMock.put("ResultNotification", Boolean.FALSE);
        resultMapMock.put("ChannelType", "EMAIL");

        try (MockedStatic<Builders> buildersMock = mockStatic(Builders.class)) {
            buildersMock.when(() -> Builders.createResultNotification(anyString(), any(), anyBoolean()))
                    .thenReturn(resultMapMock);

            CompletableFuture<Map<String, Object>> result = emailService.sendNotification(notificationDto);
            Map<String, Object> resultMap = result.get();

            assertNotNull(resultMap);
            assertEquals("EMAIL", resultMap.get("ChannelType"));
            assertFalse((Boolean) resultMap.get("ResultNotification"));
        }
    }
}