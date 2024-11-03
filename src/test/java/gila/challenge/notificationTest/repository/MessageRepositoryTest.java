package gila.challenge.notificationTest.repository;

import gila.challenge.notificationTest.common.utilities.mappers.MessageMapper;
import gila.challenge.notificationTest.dto.MessageDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.model.Message;
import gila.challenge.notificationTest.model.User;
import gila.challenge.notificationTest.service.CategoryService;
import gila.challenge.notificationTest.service.ChannelService;
import gila.challenge.notificationTest.service.MessageService;
import gila.challenge.notificationTest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class MessageRepositoryTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserService userService;

    @Mock
    private ChannelService channelService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private MessageMapper messageMapper;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMessages() {
        User user = User.builder().userId(1).userName("John Doe").build();
        Category category = Category.builder().id(1).name("Movies").build();
        Channel channel = Channel.builder().id(1).name("SMS").build();

        List<Message> mockMessages = List.of(
                Message.builder().id(1).user(user).category(category).channel(channel).content("Message 1").sentAt(LocalDateTime.now()).build(),
                Message.builder().id(2).user(user).category(category).channel(channel).content("Message 2").sentAt(LocalDateTime.now().minusDays(1)).build()
        );

        when(messageRepository.findAllMessagesOrderByDateDesc()).thenReturn(mockMessages);

        List<MessageDto> result = messageService.getAllMessages();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getUserName());
        assertEquals("Message 1", result.get(0).getContent());
        verify(messageRepository, times(1)).findAllMessagesOrderByDateDesc();
    }

}