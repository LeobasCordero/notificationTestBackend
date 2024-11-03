package gila.challenge.notificationTest.common.utilities.builders;

import gila.challenge.notificationTest.common.utilities.constants.NotificationConstants;
import gila.challenge.notificationTest.dto.NotificationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BuildersTest {

    private NotificationDto notificationDto;

    @BeforeEach
    void setUp() {
        notificationDto = NotificationDto.builder()
                .userName("John Doe")
                .categoryName("Movies")
                .channelName("PUSH_NOTIFICATION")
                .content("This is a test notification")
                .status("SENT")
                .build();
    }

    @Test
    void testCreateResultNotification_Success() {
        Map<String, Object> result = Builders.createResultNotification("PUSH_NOTIFICATION", notificationDto, true);

        assertNotNull(result);
        assertEquals("PUSH_NOTIFICATION", result.get(NotificationConstants.CHANNEL_TYPE));
        assertEquals(notificationDto, result.get(NotificationConstants.NOTIFICATION));
        assertEquals(true, result.get(NotificationConstants.RESULT));
    }

    @Test
    void testCreateResultNotification_Failure() {
        Map<String, Object> result = Builders.createResultNotification("PUSH_NOTIFICATION", notificationDto, false);

        assertNotNull(result);
        assertEquals("PUSH_NOTIFICATION", result.get(NotificationConstants.CHANNEL_TYPE));
        assertEquals(notificationDto, result.get(NotificationConstants.NOTIFICATION));
        assertEquals(false, result.get(NotificationConstants.RESULT));
    }
}
