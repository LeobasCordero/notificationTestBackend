package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategoryById(Integer categoryId){
        logger.info("CategoryService.getCategoryById starts");
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> {
                    logger.info("CategoryService.getChannelById error: No category found");
                    return new IllegalArgumentException("Category not found");
                });
    }
}
