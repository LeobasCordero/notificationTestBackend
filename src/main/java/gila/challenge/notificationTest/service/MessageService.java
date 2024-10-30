package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.dto.MessageDto;
import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.model.Message;
import gila.challenge.notificationTest.model.User;
import gila.challenge.notificationTest.repository.MessageRepository;
import gila.challenge.notificationTest.utilities.mappers.MessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private CategoryService categoryService;

    public List<MessageDto> getAllMessages(){
        logger.info("MessageService.getAllMessages starts");
        var messagesList = messageRepository.findAllMessagesOrderByDateDesc();

        return messagesList.stream().map(MessageMapper::messageToMessageDto).toList();
    }

    public Message saveMessage(NotificationDto notificationDto, String channelName) {
        logger.info("MessageService.saveMessage starts");
        User user = userService.getUserById(notificationDto.getUserId());
        Category category = categoryService.getCategoryById(notificationDto.getCategoryId());
        Channel channel = channelService.getChannelByName(channelName);
        var message = MessageMapper.NotificationDTOtoMessage(user, category, channel, notificationDto);
        message.setSentAt(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));

        logger.info("MessageService.saveMessage: storing message for userID: {}", user.getUserId());
        return messageRepository.save(message);
    }
}
