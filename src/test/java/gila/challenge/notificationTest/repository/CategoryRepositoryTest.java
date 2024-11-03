package gila.challenge.notificationTest.repository;

import gila.challenge.notificationTest.common.utilities.mappers.CategoryMapper;
import gila.challenge.notificationTest.dto.CategoryDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryRepositoryTest {

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
    void testFindById_Success() {
        Category category = Category.builder()
                .id(1)
                .name("MOVIES")
                .displayName("Movies")
                .build();

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryById(1);

        assertNotNull(result);
        assertEquals("MOVIES", result.getName());
        verify(categoryRepository, times(1)).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        Category result = categoryService.getCategoryById(1);

        assertNotNull(result);
        assertNull(result.getId());
        verify(categoryRepository, times(1)).findById(1);
    }

    @Test
    void testFindAll() {
        List<Category> mockCategories = List.of(
                Category.builder().id(1).name("MOVIES").displayName("Movies").build(),
                Category.builder().id(2).name("SPORTS").displayName("Sports").build()
        );

        when(categoryRepository.findAll()).thenReturn(mockCategories);
        when(categoryMapper.categoryToCategoryDto(any(Category.class))).thenAnswer(invocation -> {
            Category category = invocation.getArgument(0);
            return CategoryDto.builder().id(category.getId()).name(category.getName()).build();
        });

        List<CategoryDto> result = categoryService.getAllCategories();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals("MOVIES", result.get(0).getName());
        assertEquals("SPORTS", result.get(1).getName());
        verify(categoryRepository, times(1)).findAll();
    }
}
