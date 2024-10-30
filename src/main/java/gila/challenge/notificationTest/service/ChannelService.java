package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private static final Logger logger = LoggerFactory.getLogger(ChannelService.class);

    @Autowired
    private ChannelRepository channelRepository;

    public Channel getChannelById(Integer channelId){
        logger.info("ChannelService.getChannelById starts");
        return channelRepository.findById(channelId)
                .orElseThrow(() -> {
                    logger.info("ChannelService.getChannelById error: No channel found");
                    return new IllegalArgumentException("Channel not found");
                });
    }

    public Channel getChannelByName(String channelName){
        return channelRepository.findByName(channelName)
                .orElseThrow(() -> {
                    logger.info("ChannelService.findByChannelName error: No channel found");
                    return new IllegalArgumentException("Channel not found");
                });
    }
}
