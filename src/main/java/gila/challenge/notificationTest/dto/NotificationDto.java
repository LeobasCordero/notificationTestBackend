package gila.challenge.notificationTest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDto {

    private String userName;
    private String categoryName;
    private String channelName;
    private String content;
    private LocalDateTime sentAt;

    private Integer userId;
    private Integer categoryId;
    private Integer channelId;
}
