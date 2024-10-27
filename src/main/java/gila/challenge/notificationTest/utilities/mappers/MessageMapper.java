package gila.challenge.notificationTest.utilities.mappers;


import gila.challenge.notificationTest.dto.MessageDto;
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
        messageDto.setChannelName(message.getChannel().getName());

        return messageDto;
    }

    public static Message MessageDTOtoMessage(User user, Category category, Channel channel, MessageDto messageDto){
        var message = new Message();

        message.setCategory(category);
        message.setChannel(channel);
        message.setUser(user);
        message.setSentAt(messageDto.getSentAt());
        message.setContent(message.getContent());

        return message;
    }
}
