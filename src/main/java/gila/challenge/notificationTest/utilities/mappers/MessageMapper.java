package gila.challenge.notificationTest.utilities.mappers;


import gila.challenge.notificationTest.dto.MessageDto;
import gila.challenge.notificationTest.model.Message;
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
}
