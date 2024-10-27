package gila.challenge.notificationTest.controller;

import gila.challenge.notificationTest.api.UserApi;
import gila.challenge.notificationTest.dto.UserDto;
import gila.challenge.notificationTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Api(tags = "Users API")
@RestController
@RequestMapping("/api")
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
