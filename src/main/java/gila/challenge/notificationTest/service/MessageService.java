package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.dto.MessageDto;
import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.model.Message;
import gila.challenge.notificationTest.model.User;
import gila.challenge.notificationTest.repository.MessageRepository;
import gila.challenge.notificationTest.common.utilities.mappers.MessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static gila.challenge.notificationTest.common.utilities.constants.ErrorConstants.SAVE_SAVE_MESSAGE_ERROR;

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

    @Transactional
    public Message saveMessage(NotificationDto notificationDto, String channelName, String status) {
        logger.info("MessageService.saveMessage - Start: userId={}, categoryId={}, channelName={}",
                notificationDto.getUserId(), notificationDto.getCategoryId(), channelName);

        try{
            // Fetch related entities
            User user = userService.getUserById(notificationDto.getUserId());
            Category category = categoryService.getCategoryById(notificationDto.getCategoryId());
            Channel channel = channelService.getChannel(channelName);

            // Create message
            var message = MessageMapper.NotificationDTOtoMessage(user, category, channel, notificationDto);

            // Set standard message properties
            message.setSentAt(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
            message.setStatus(status);

            // Save and log
            logger.info("MessageService.saveMessage: storing message for userID: {}", user.getUserId());
            return messageRepository.save(message);
        } catch (Exception e) {
            logger.error("MessageService.saveMessage - Error: userId={}, error={}",
                    notificationDto.getUserId(), e.getMessage(), e);
            throw new RuntimeException(SAVE_SAVE_MESSAGE_ERROR, e);
        }
    }

}
