package gila.challenge.notificationTest.service;

import gila.challenge.notificationTest.common.utilities.mappers.CategoryMapper;
import gila.challenge.notificationTest.dto.CategoryDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCategoryById_Success() {
        Category category = Category.builder().id(1).name("Movies").build();
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Movies", result.getName());
        verify(categoryRepository, times(1)).findById(1);
    }

    @Test
    void testGetCategoryById_NotFound() {
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        Category result = categoryService.getCategoryById(1);

        assertNotNull(result);
        assertNull(result.getId());
        verify(categoryRepository, times(1)).findById(1);
    }

    @Test
    void testGetCategoryById_Error() {
        when(categoryRepository.findById(1)).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            categoryService.getCategoryById(1);
        });

        assertEquals("Database error", exception.getCause().getMessage());
        verify(categoryRepository, times(1)).findById(1);
    }

    @Test
    void testGetAllCategories() {
        List<Category> mockCategories = Arrays.asList(
                Category.builder().id(1).name("Movies").build(),
                Category.builder().id(2).name("Sports").build()
        );

        when(categoryRepository.findAll()).thenReturn(mockCategories);
        when(categoryMapper.categoryToCategoryDto(any(Category.class))).thenAnswer(invocation -> {
            Category category = invocation.getArgument(0);
            return CategoryDto.builder().id(category.getId()).name(category.getName()).build();
        });

        List<CategoryDto> result = categoryService.getAllCategories();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Movies", result.get(0).getName());
        assertEquals("Sports", result.get(1).getName());
        verify(categoryRepository, times(1)).findAll();
    }
}
