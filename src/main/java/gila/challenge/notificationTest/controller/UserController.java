package gila.challenge.notificationTest.controller;

import gila.challenge.notificationTest.api.UserApi;
import gila.challenge.notificationTest.dto.UserDto;
import gila.challenge.notificationTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

//@Api(tags = "Users API")
@RestController
@RequestMapping("/api")
public class UserController implements UserApi {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Override
    public List<UserDto> getAllUsers() {
        logger.info("UserController.getAllUsers starts");
        return userService.getAllUsers();
    }
}
