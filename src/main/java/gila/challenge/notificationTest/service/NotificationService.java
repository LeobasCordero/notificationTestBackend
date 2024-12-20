package gila.challenge.notificationTest.service;


import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.model.User;
import gila.challenge.notificationTest.service.Interfaces.Notification;
import gila.challenge.notificationTest.common.utilities.enums.ChannelType;
import gila.challenge.notificationTest.common.utilities.factories.NotificationFactory;
import gila.challenge.notificationTest.common.utilities.validators.NotificationValidations;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationFactory notificationFactory;

    private final UserService userService;

    public void send(NotificationDto notificationDto){
        logger.info("NotificationService.send starts");
        NotificationValidations.validateNotificationToStore(notificationDto);
        User user = userService.getUserById(notificationDto.getUserId());
        if(Objects.nonNull(user.getChannels())) {
            user.getChannels().forEach(channel -> sendNotificationByChannel(channel, notificationDto));
        }
    }

    private void sendNotificationByChannel(Channel channel, NotificationDto notificationDto) {
        Notification notification = getNotificationService(channel.getName());
        if(Objects.nonNull(notification)){
            notification.sendNotification(notificationDto);
        }else{
            logger.error("NotificationService.send error: Channel not supported");
        }
    }

    private Notification getNotificationService(String channelName) {
        logger.info("NotificationService.getNotificationService starts for channelName: {}", channelName);
        ChannelType channelType = ChannelType.fromValue(channelName);
        return channelType != null ? channelType.getService(notificationFactory) : null;
    }

}
