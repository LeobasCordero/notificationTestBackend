package gila.challenge.notificationTest.helpers;

import gila.challenge.notificationTest.dto.CategoryDto;

public class Helpers {

    public static CategoryDto createCategoryDto(Integer id, String name) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(id);
        categoryDto.setName(name);
        return categoryDto;
    }
}
