package gila.challenge.notificationTest.utilities.mappers;

import gila.challenge.notificationTest.dto.UserDto;
import gila.challenge.notificationTest.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto userModelToUserDto(User user){
        var userDto = new UserDto();

        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());

        return userDto;
    }
}
