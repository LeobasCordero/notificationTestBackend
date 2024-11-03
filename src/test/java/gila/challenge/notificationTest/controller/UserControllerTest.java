package gila.challenge.notificationTest.controller;

import gila.challenge.notificationTest.common.utilities.mappers.CategoryMapper;
import gila.challenge.notificationTest.dto.CategoryDto;
import gila.challenge.notificationTest.dto.UserDto;
import gila.challenge.notificationTest.model.Category;
import gila.challenge.notificationTest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<UserDto> mockUsers = List.of(
                UserDto.builder().userId(1).userName("John Doe").build(),
                UserDto.builder().userId(2).userName("Jane Doe").build()
        );

        when(userService.getAllUsers()).thenReturn(mockUsers);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userName", is("John Doe")))
                .andExpect(jsonPath("$[1].userName", is("Jane Doe")));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetUserCategories() throws Exception {
        List<Category> categories = List.of(
                Category.builder().id(1).name("Movies").build(),
                Category.builder().id(2).name("Sports").build()
        );
        List<CategoryDto> categoryDtos = List.of(
                CategoryDto.builder().id(1).name("Movies").build(),
                CategoryDto.builder().id(2).name("Sports").build()
        );

        when(userService.getCategoriesByUserId(1)).thenReturn(categories);
        when(categoryMapper.categoryToCategoryDto(any(Category.class))).thenAnswer(invocation -> {
            Category category = invocation.getArgument(0);
            return CategoryDto.builder().id(category.getId()).name(category.getName()).build();
        });

        mockMvc.perform(get("/api/users/1/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Movies")))
                .andExpect(jsonPath("$[1].name", is("Sports")));

        verify(userService, times(1)).getCategoriesByUserId(1);
        verify(categoryMapper, times(2)).categoryToCategoryDto(any(Category.class));
    }
}
