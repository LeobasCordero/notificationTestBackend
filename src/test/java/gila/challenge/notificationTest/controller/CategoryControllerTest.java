package gila.challenge.notificationTest.controller;

import gila.challenge.notificationTest.dto.CategoryDto;
import gila.challenge.notificationTest.helpers.Helpers;
import gila.challenge.notificationTest.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
@AutoConfigureMockMvc
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    void getAllCategories_ShouldReturnCategoriesList() throws Exception {
        // Arrange
        List<CategoryDto> categories = Arrays.asList(
                Helpers.createCategoryDto(1, "Email"),
                Helpers.createCategoryDto(2, "SMS"),
                Helpers.createCategoryDto(3, "Push Notification")
        );

        when(categoryService.getAllCategories()).thenReturn(categories);

        // Act & Assert
        mockMvc.perform(get("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Email"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("SMS"))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].name").value("Push Notification"))
                .andDo(print());

        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    void getAllCategories_WhenEmpty_ShouldReturnEmptyList() throws Exception {
        // Arrange
        when(categoryService.getAllCategories()).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andDo(print());

        verify(categoryService, times(1)).getAllCategories();
    }

}