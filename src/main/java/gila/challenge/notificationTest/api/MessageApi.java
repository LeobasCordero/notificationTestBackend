package gila.challenge.notificationTest.api;

import gila.challenge.notificationTest.dto.MessageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


public interface MessageApi {

    @Operation(summary = "Fetch all messages", description = "Fetch all messages from DB", tags = {"message"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR + "", description = "Internal server error"),
            @ApiResponse(responseCode = HttpServletResponse.SC_OK + "", description = "All messages from database")
    }
    )
    @GetMapping("/messages")
    @ResponseStatus(HttpStatus.OK)
    List<MessageDto> getAllMessages();
}
