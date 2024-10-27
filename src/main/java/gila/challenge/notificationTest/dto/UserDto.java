package gila.challenge.notificationTest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    
    private String userName;
    private String email;
    private String phoneNumber;

}