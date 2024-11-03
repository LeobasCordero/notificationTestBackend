package gila.challenge.notificationTest.common.utilities.mappers;


import gila.challenge.notificationTest.dto.UserDto;
import gila.challenge.notificationTest.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void testUserModelToUserDto() {
        User user = User.builder()
                .userId(1)
                .userName("John Doe")
                .build();

        UserDto userDto = UserMapper.userModelToUserDto(user);

        assertNotNull(userDto);
        assertEquals(user.getUserId(), userDto.getUserId());
        assertEquals(user.getUserName(), userDto.getUserName());
    }

    @Test
    void testUserModelToUserDto_NullUser() {
        UserDto userDto = UserMapper.userModelToUserDto(new User());

        assertNull(userDto.getUserId());
        assertNull(userDto.getUserName());
    }
}
