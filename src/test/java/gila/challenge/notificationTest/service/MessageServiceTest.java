package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.dto.MessageDto;
import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.model.Message;
import gila.challenge.notificationTest.model.User;
import gila.challenge.notificationTest.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserService userService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ChannelService channelService;

    @InjectMocks
    private MessageService messageService;

    private NotificationDto notificationDto;
    private User user;
    private Category category;
    private Channel channel;
    private Message message;
    private List<Message> messageList;

    @BeforeEach
    void setUp() {
        notificationDto = NotificationDto.builder()
                .userId(1)
                .categoryId(1)
                .channelId(1)
                .content("Test message")
                .build();

        user = User.builder()
                .userId(1)
                .userName("Test User")
                .email("test@test.com")
                .build();

        category = Category.builder()
                .id(1)
                .name("Test Category")
                .build();

        channel = Channel.builder()
                .id(1)
                .name("Test Channel")
                .build();

        message = Message.builder()
                .id(1)
                .userId(1)
                .categoryId(1)
                .channelId(1)
                .content("Test message")
                .sentAt(LocalDateTime.now())
                .build();

        messageList = Arrays.asList(message);
    }

    @Test
    @DisplayName("Should get all messages")
    void shouldGetAllMessages() {
        when(messageRepository.findAllMessagesOrderByDateDesc()).thenReturn(messageList);

        List<MessageDto> result = messageService.getAllMessages();

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        verify(messageRepository, times(1)).findAllMessagesOrderByDateDesc();
    }

    @Test
    @DisplayName("Should save message")
    void shouldSaveMessage() {

        when(userService.getUserById(anyInt())).thenReturn(user);
        when(categoryService.getCategoryById(anyInt())).thenReturn(category);
        when(channelService.getChannelById(anyInt())).thenReturn(channel);
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        Message result = messageService.saveMessage(notificationDto, "SMS", "SENT");

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        verify(messageRepository, times(1)).save(any(Message.class));
    }

    @Test
    @DisplayName("Should throw exception when user not found")
    void shouldThrowExceptionWhenUserNotFound() {

        when(userService.getUserById(anyInt()))
                .thenThrow(new RuntimeException("User not found"));

        assertThrows(RuntimeException.class, () ->
                messageService.saveMessage(notificationDto, "SMS", "FAILED"));
        verify(messageRepository, never()).save(any(Message.class));
    }
}