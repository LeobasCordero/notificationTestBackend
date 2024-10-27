package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.dto.MessageDto;
import gila.challenge.notificationTest.repository.MessageRepository;
import gila.challenge.notificationTest.utilities.mappers.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<MessageDto> getAllMessages(){
        var messagesList = messageRepository.findAllMessagesOrderByDateDesc();

        return messagesList.stream().map(MessageMapper::messageToMessageDto).toList();
    }
}
