package gila.challenge.notificationTest.common.utilities.mappers;

import gila.challenge.notificationTest.dto.MessageDto;
import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.model.Message;
import gila.challenge.notificationTest.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MessageMapperTest {

    @Test
    void testMessageToMessageDto() {
        User user = User.builder().userId(1).userName("John Doe").build();
        Category category = Category.builder().id(1).name("Movies").build();
        Channel channel = Channel.builder().id(1).displayName("SMS").build();
        Message message = Message.builder()
                .user(user)
                .category(category)
                .channel(channel)
                .content("This is a test message")
                .status("SENT")
                .sentAt(LocalDateTime.now())
                .build();

        MessageDto messageDto = MessageMapper.messageToMessageDto(message);

        assertNotNull(messageDto);
        assertEquals("John Doe", messageDto.getUserName());
        assertEquals("Movies", messageDto.getCategoryName());
        assertEquals("SMS", messageDto.getChannelName());
        assertEquals("This is a test message", messageDto.getContent());
        assertEquals("SENT", messageDto.getStatus());
    }

    @Test
    void testNotificationDTOtoMessage() {
        User user = User.builder().userId(1).userName("John Doe").build();
        Category category = Category.builder().id(1).name("Movies").build();
        Channel channel = Channel.builder().id(1).displayName("SMS").build();

        NotificationDto notificationDto = NotificationDto.builder()
                .userId(1)
                .categoryId(1)
                .channelId(1)
                .userName("John Doe")
                .categoryName("Movies")
                .channelName("SMS")
                .content("This is a test notification")
                .status("SENT")
                .build();

        Message message = MessageMapper.NotificationDTOtoMessage(user, category, channel, notificationDto);

        assertNotNull(message);
        assertEquals(user, message.getUser());
        assertEquals(category, message.getCategory());
        assertEquals(channel, message.getChannel());
        assertEquals("This is a test notification", message.getContent());
    }
}
