package gila.challenge.notificationTest.common.utilities.mappers;

import gila.challenge.notificationTest.dto.CategoryDto;
import gila.challenge.notificationTest.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryDto categoryToCategoryDto(Category category);

}
