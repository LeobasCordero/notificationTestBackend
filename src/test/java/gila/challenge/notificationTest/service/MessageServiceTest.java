package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.dto.MessageDto;
import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.model.Message;
import gila.challenge.notificationTest.model.User;
import gila.challenge.notificationTest.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserService userService;

    @Mock
    private ChannelService channelService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMessages() {
        List<Message> mockMessages = Arrays.asList(
                Message.builder().id(1).content("Message 1").sentAt(LocalDateTime.now()).build(),
                Message.builder().id(2).content("Message 2").sentAt(LocalDateTime.now()).build()
        );

        when(messageRepository.findAllMessagesOrderByDateDesc()).thenReturn(mockMessages);

        List<MessageDto> result = messageService.getAllMessages();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(messageRepository, times(1)).findAllMessagesOrderByDateDesc();
    }

    @Test
    void testSaveMessage_Success() {
        NotificationDto notificationDto = NotificationDto.builder()
                .userId(1)
                .categoryId(2)
                .channelName("SMS")
                .content("This is a test notification")
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .build();

        User user = User.builder().userId(1).build();
        Category category = Category.builder().id(2).build();
        Channel channel = Channel.builder().id(3).name("SMS").build();

        Message message = Message.builder()
                .userId(user.getUserId())
                .categoryId(category.getId())
                .channelId(channel.getId())
                .content(notificationDto.getContent())
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .user(user)
                .category(category)
                .channel(channel)
                .build();

        when(userService.getUserById(notificationDto.getUserId())).thenReturn(user);
        when(categoryService.getCategoryById(notificationDto.getCategoryId())).thenReturn(category);
        when(channelService.getChannel(notificationDto.getChannelName())).thenReturn(channel);
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        Message result = messageService.saveMessage(notificationDto, "SMS", "SENT");

        assertNotNull(result);
        verify(userService, times(1)).getUserById(notificationDto.getUserId());
        verify(categoryService, times(1)).getCategoryById(notificationDto.getCategoryId());
        verify(channelService, times(1)).getChannel(notificationDto.getChannelName());
        verify(messageRepository, times(1)).save(any(Message.class));
    }

    @Test
    void testSaveMessage_Error() {
        NotificationDto notificationDto = NotificationDto.builder()
                .userId(1)
                .categoryId(2)
                .channelName("SMS")
                .content("This is a test notification")
                .status("FAILED")
                .sentAt(LocalDateTime.now())
                .build();

        when(userService.getUserById(notificationDto.getUserId())).thenThrow(new RuntimeException("User not found"));

        assertThrows(RuntimeException.class, () -> {
            messageService.saveMessage(notificationDto, "SMS", "FAILED");
        });

        verify(userService, times(1)).getUserById(notificationDto.getUserId());
        verify(categoryService, never()).getCategoryById(notificationDto.getCategoryId());
        verify(channelService, never()).getChannel(notificationDto.getChannelName());
        verify(messageRepository, never()).save(any(Message.class));
    }
}
