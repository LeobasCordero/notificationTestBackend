package gila.challenge.notificationTest.controller;

import gila.challenge.notificationTest.api.UserApi;
import gila.challenge.notificationTest.dto.CategoryDto;
import gila.challenge.notificationTest.dto.UserDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.service.UserService;
import gila.challenge.notificationTest.utilities.mappers.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController implements UserApi {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<UserDto> getAllUsers() {
        logger.info("UserController.getAllUsers starts");
        return userService.getAllUsers();
    }

    @Override
    public List<CategoryDto> getUserCategories(Integer userId) {
        List<Category> categories = userService.getCategoriesByUserId(userId);

        // Map Category entities to DTOs
        return categories.stream()
                .map(category -> categoryMapper.categoryToCategoryDto(category))
                .toList();
    }
}
