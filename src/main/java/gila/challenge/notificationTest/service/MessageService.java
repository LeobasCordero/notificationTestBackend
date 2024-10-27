package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.dto.MessageDto;
import gila.challenge.notificationTest.model.Message;
import gila.challenge.notificationTest.model.User;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.repository.MessageRepository;
import gila.challenge.notificationTest.utilities.mappers.MessageMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private CategoryService categoryService;

    public List<MessageDto> getAllMessages(){
        var messagesList = messageRepository.findAllMessagesOrderByDateDesc();

        return messagesList.stream().map(MessageMapper::messageToMessageDto).toList();
    }

    public Message saveMessage(MessageDto messageDto) {
        User user = userService.getUserById(messageDto.getUserId());
        Category category = categoryService.getCategoryById(messageDto.getCategoryId());
        Channel channel = channelService.getChannelById(messageDto.getChannelId());

        var message = MessageMapper.MessageDTOtoMessage(user, category, channel, messageDto);

        return messageRepository.save(message);
    }
}
