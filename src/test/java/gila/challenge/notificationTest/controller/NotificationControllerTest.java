package gila.challenge.notificationTest.controller;

import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificationController.class)
@AutoConfigureMockMvc
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendNotification() throws Exception {
        doNothing().when(notificationService).send(any(NotificationDto.class));

        mockMvc.perform(post("/api/notification/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"userName\": \"John Doe\", \"categoryName\": \"Movies\", \"channelName\": \"PUSH_NOTIFICATION\", \"content\": \"This is a test notification\", \"status\": \"SENT\" }"))
                .andExpect(status().isOk());

        verify(notificationService, times(1)).send(any(NotificationDto.class));
    }
}
