package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static gila.challenge.notificationTest.common.utilities.constants.ErrorConstants.CHANNEL_ERROR_MSG;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private static final Logger logger = LoggerFactory.getLogger(ChannelService.class);

    @Autowired
    private ChannelRepository channelRepository;

    public Channel getChannel(Integer channelId){
        logger.info("ChannelService.getChannel by id starts");
        Optional<Channel> channel;
        try{
            channel = channelRepository.findById(channelId);
        } catch (Exception e) {
            logger.info("ChannelService.getChannel error: No channel found for ID: {}", channelId);
            throw new RuntimeException(CHANNEL_ERROR_MSG, e);
        }
        return channel.orElseGet(Channel::new);
    }

    public Channel getChannel(String channelName){
        logger.info("ChannelService.getChannel by name starts");
        Optional<Channel> channel;
        try{
            channel = channelRepository.findByName(channelName);
        } catch (Exception e) {
            logger.info("ChannelService.findByChannelName error: No channel found for ChannelName: {}", channelName);
            throw new RuntimeException(e);
        }
        return channel.orElseGet(Channel::new);
    }
}
