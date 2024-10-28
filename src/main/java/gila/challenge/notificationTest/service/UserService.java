package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.dto.UserDto;
import gila.challenge.notificationTest.model.User;
import gila.challenge.notificationTest.repository.UserRepository;
import gila.challenge.notificationTest.utilities.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        logger.info("UserService.getAllUsers starts");
        var userList = userRepository.findAll();
        return userList.stream().map(UserMapper::userModelToUserDto).toList();
    }

    public User getUserById(Integer id){
        logger.info("UserService.getUserById starts with id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
