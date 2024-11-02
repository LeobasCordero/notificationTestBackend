package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.dto.UserDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.model.User;
import gila.challenge.notificationTest.repository.UserRepository;
import gila.challenge.notificationTest.common.utilities.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static gila.challenge.notificationTest.common.utilities.constants.ErrorConstants.USER_ERROR_MSG;

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
        Optional<User> user;
        try{
            user = userRepository.findById(id);
        } catch (Exception e) {
            logger.info("UserService.getUserById starts with id: {}", id);
            throw new RuntimeException(USER_ERROR_MSG, e);
        }
        return user.orElseGet(User::new);
    }

    public List<Category> getCategoriesByUserId(Integer userId) {
        User user = getUserById(userId);
        return user.getCategories();
    }
}
