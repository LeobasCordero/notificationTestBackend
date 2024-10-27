package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.dto.UserDto;
import gila.challenge.notificationTest.repository.UserRepository;
import gila.challenge.notificationTest.utilities.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        var userList = userRepository.findAll();

        return userList.stream().map(UserMapper::userModelToUserDto).toList();
    }
}
