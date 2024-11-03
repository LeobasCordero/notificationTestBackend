package gila.challenge.notificationTest.common.utilities.mappers;


import gila.challenge.notificationTest.dto.MessageDto;
import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.model.Message;
import gila.challenge.notificationTest.model.User;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class MessageMapper {

    public static MessageDto messageToMessageDto(Message message){
        var messageDto = new MessageDto();

        if(Objects.nonNull(message)) {
            messageDto.setUserName(Optional.ofNullable(message.getUser())
                    .map(User::getUserName)
                    .orElse(null));

            messageDto.setCategoryName(Optional.ofNullable(message.getCategory())
                    .map(Category::getName)
                    .orElse(null));

            messageDto.setChannelName(Optional.ofNullable(message.getChannel())
                    .map(Channel::getDisplayName)
                    .orElse(null));

            messageDto.setSentAt(message.getSentAt());
            messageDto.setContent(message.getContent());
            messageDto.setStatus(message.getStatus());
        }
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
