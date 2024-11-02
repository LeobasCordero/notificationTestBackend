package gila.challenge.notificationTest.common.utilities.validators;

import gila.challenge.notificationTest.dto.NotificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static gila.challenge.notificationTest.common.utilities.constants.ErrorConstants.*;

public class NotificationValidations {
    private static final Logger logger = LoggerFactory.getLogger(NotificationValidations.class);

    public static void validateNotificationToStore(NotificationDto notificationDto){
        logger.info("NotificationValidations.validateNotificationToStore starts");
        if(Objects.isNull(notificationDto)){
            throw new RuntimeException(NOTIFICATION_ERROR_MSG);
        }

        if (Objects.isNull(notificationDto.getUserId())) {
            throw new IllegalArgumentException(USER_ID_REQUIRED);
        }
        if (Objects.isNull(notificationDto.getCategoryId())) {
            throw new IllegalArgumentException(CATEGORY_ID_REQUIRED);
        }
    }

}
