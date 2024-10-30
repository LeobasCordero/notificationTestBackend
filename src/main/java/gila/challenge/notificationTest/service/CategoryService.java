package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.dto.CategoryDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.repository.CategoryRepository;
import gila.challenge.notificationTest.utilities.mappers.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static gila.challenge.notificationTest.utilities.constants.ErrorConstants.CATEGORY_ERROR_MSG;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final CategoryMapper categoryMapper;

    public Category getCategoryById(Integer categoryId){
        logger.info("CategoryService.getCategoryById starts");
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> {
                    logger.info("CategoryService.getChannelById error: No category found");
                    return new IllegalArgumentException(CATEGORY_ERROR_MSG);
                });
    }

    public List<CategoryDto> getAllCategories(){
        logger.info("CategoryService.getAllCategories starts");

        var categoryList = categoryRepository.findAll();
        return categoryList.stream().map(categoryMapper::categoryToCategoryDto).toList();
    }
}
