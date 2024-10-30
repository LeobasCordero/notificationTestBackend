package gila.challenge.notificationTest.api;

import gila.challenge.notificationTest.dto.CategoryDto;
import gila.challenge.notificationTest.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


public interface UserApi {

    @Operation(summary = "Fetch all users", description = "Fetch all user data from DB", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR + "", description = "Internal server error"),
            @ApiResponse(responseCode = HttpServletResponse.SC_OK + "", description = "All users from database")
    }
    )
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    List<UserDto> getAllUsers();

    @Operation(summary = "Fetch all users categories", description = "Fetch all user categories from DB", tags = {"user", "category"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR + "", description = "Internal server error"),
            @ApiResponse(responseCode = HttpServletResponse.SC_OK + "", description = "All user categories from database")
    }
    )
    @GetMapping("/users/{userId}/categories")
    @ResponseStatus(HttpStatus.OK)
    List<CategoryDto> getUserCategories(@PathVariable Integer userId);
}
