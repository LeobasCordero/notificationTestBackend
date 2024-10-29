package gila.challenge.notificationTest.api;

import gila.challenge.notificationTest.dto.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


public interface CategoryApi {

    @Operation(summary = "Fetch all categories", description = "Fetch all categories data from DB", tags = {"category"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR + "", description = "Internal server error"),
            @ApiResponse(responseCode = HttpServletResponse.SC_OK + "", description = "All categories from database")
    }
    )
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    List<CategoryDto> getAllCategories();
}
