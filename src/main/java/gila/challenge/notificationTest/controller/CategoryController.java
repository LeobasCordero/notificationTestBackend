package gila.challenge.notificationTest.controller;

import gila.challenge.notificationTest.api.CategoryApi;
import gila.challenge.notificationTest.dto.CategoryDto;
import gila.challenge.notificationTest.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController implements CategoryApi {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;


    @Override
    public List<CategoryDto> getAllCategories() {
        logger.info("CategoryController.getAllCategories starts");
        return categoryService.getAllCategories();
    }
}
