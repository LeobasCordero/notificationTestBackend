package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    public Channel getChannelById(Integer channelId){
        return channelRepository.findById(channelId)
                .orElseThrow(() -> new IllegalArgumentException("Channel not found"));
    }
}
