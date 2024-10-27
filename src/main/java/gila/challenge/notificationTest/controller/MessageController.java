package gila.challenge.notificationTest.controller;

import gila.challenge.notificationTest.api.MessageApi;
import gila.challenge.notificationTest.dto.MessageDto;
import gila.challenge.notificationTest.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController implements MessageApi {

    @Autowired
    private MessageService messageService;

    @Override
    public List<MessageDto> getAllMessages() {
        return messageService.getAllMessages();
    }
}
