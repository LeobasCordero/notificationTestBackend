package gila.challenge.notificationTest.controller;

import gila.challenge.notificationTest.dto.MessageDto;
import gila.challenge.notificationTest.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMessages() throws Exception {
        List<MessageDto> mockMessages = List.of(
                MessageDto.builder().userName("John Doe").categoryName("Movies").channelName("SMS").content("Message 1").build(),
                MessageDto.builder().userName("Jane Doe").categoryName("Sports").channelName("Email").content("Message 2").build()
        );

        when(messageService.getAllMessages()).thenReturn(mockMessages);

        mockMvc.perform(get("/api/messages")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userName", is("John Doe")))
                .andExpect(jsonPath("$[1].userName", is("Jane Doe")));

        verify(messageService, times(1)).getAllMessages();
    }
}
