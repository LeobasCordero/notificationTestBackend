package gila.challenge.notificationTest.utilities.validators;

import gila.challenge.notificationTest.dto.NotificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static gila.challenge.notificationTest.utilities.constants.ErrorConstants.NOTIFICATION_ERROR_MSG;

public class NotificationValidations {
    private static final Logger logger = LoggerFactory.getLogger(NotificationValidations.class);

    public static void validateNotificationToStore(NotificationDto notificationDto){
        logger.info("NotificationValidations.validateNotificationToStore starts");
        if(Objects.isNull(notificationDto)){
            throw new RuntimeException(NOTIFICATION_ERROR_MSG);
        }
    }
}
