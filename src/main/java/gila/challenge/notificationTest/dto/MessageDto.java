package gila.challenge.notificationTest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private String userName;
    private String categoryName;
    private String channelName;
    private String content;
    private String status;
    private LocalDateTime sentAt;
}