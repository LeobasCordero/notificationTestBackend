package gila.challenge.notificationTest.service;


import gila.challenge.notificationTest.model.Channel;
import gila.challenge.notificationTest.repository.ChannelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChannelServiceTest {

    @Mock
    private ChannelRepository channelRepository;

    @InjectMocks
    private ChannelService channelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetChannelById_Success() {
        Channel channel = Channel.builder().id(1).name("SMS").build();

        when(channelRepository.findById(1)).thenReturn(Optional.of(channel));

        Channel result = channelService.getChannel(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("SMS", result.getName());
        verify(channelRepository, times(1)).findById(1);
    }

    @Test
    void testGetChannelById_NotFound() {
        when(channelRepository.findById(1)).thenReturn(Optional.empty());

        Channel result = channelService.getChannel(1);

        assertNotNull(result);
        assertNull(result.getId());
        verify(channelRepository, times(1)).findById(1);
    }

    @Test
    void testGetChannelById_Error() {
        when(channelRepository.findById(1)).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            channelService.getChannel(1);
        });

        assertEquals("Database error", exception.getCause().getMessage());
        verify(channelRepository, times(1)).findById(1);
    }

    @Test
    void testGetChannelByName_Success() {
        Channel channel = Channel.builder().id(1).name("SMS").build();

        when(channelRepository.findByName("SMS")).thenReturn(Optional.of(channel));

        Channel result = channelService.getChannel("SMS");

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("SMS", result.getName());
        verify(channelRepository, times(1)).findByName("SMS");
    }

    @Test
    void testGetChannelByName_NotFound() {
        when(channelRepository.findByName("SMS")).thenReturn(Optional.empty());

        Channel result = channelService.getChannel("SMS");

        assertNotNull(result);
        assertNull(result.getId());
        verify(channelRepository, times(1)).findByName("SMS");
    }

    @Test
    void testGetChannelByName_Error() {
        when(channelRepository.findByName("SMS")).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            channelService.getChannel("SMS");
        });

        assertEquals("Database error", exception.getCause().getMessage());
        verify(channelRepository, times(1)).findByName("SMS");
    }
}
