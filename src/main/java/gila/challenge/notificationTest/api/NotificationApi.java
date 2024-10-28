package gila.challenge.notificationTest.api;

import gila.challenge.notificationTest.dto.NotificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


public interface NotificationApi {

    @Operation(summary = "Send a message", description = "Sends a message to the specific category, channel", tags = {"notification"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR + "", description = "Internal server error"),
            @ApiResponse(responseCode = HttpServletResponse.SC_OK + "", description = "All messages were sent")
    }
    )
    @PostMapping(path = "/send")
    @ResponseStatus(HttpStatus.OK)
    boolean sendNotification(@RequestBody NotificationDto messageDto);
}
