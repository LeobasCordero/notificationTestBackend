package gila.challenge.notificationTest.utilities.mappers;


import gila.challenge.notificationTest.dto.MessageDto;
import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.model.Message;
import gila.challenge.notificationTest.model.User;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public static MessageDto messageToMessageDto(Message message){
        var messageDto = new MessageDto();

        messageDto.setUserName(message.getUser().getUserName());
        messageDto.setContent(message.getContent());
        messageDto.setCategoryName(message.getCategory().getName());
        messageDto.setSentAt(message.getSentAt());
        messageDto.setChannelName(message.getChannel().getDisplayName());

        return messageDto;
    }

    public static Message NotificationDTOtoMessage(User user, Category category, Channel channel, NotificationDto notificationDto){
        var message = new Message();

        message.setCategory(category);
        message.setCategoryId(category.getId());
        message.setChannel(channel);
        message.setChannelId(channel.getId());
        message.setUser(user);
        message.setUserId(user.getUserId());
        message.setContent(notificationDto.getContent());

        return message;
    }
}
