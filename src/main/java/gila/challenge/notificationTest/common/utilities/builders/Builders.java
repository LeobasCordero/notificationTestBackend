package gila.challenge.notificationTest.common.utilities.builders;

import gila.challenge.notificationTest.common.utilities.constants.NotificationConstants;
import gila.challenge.notificationTest.dto.NotificationDto;

import java.util.HashMap;
import java.util.Map;

public class Builders {

    public static Map<String, Object> createResultNotification(String channelType, NotificationDto notificationDto, boolean resultNotification){
        Map<String, Object> result = new HashMap<>();
        result.put(NotificationConstants.CHANNEL_TYPE, channelType);
        result.put(NotificationConstants.NOTIFICATION, notificationDto);
        result.put(NotificationConstants.RESULT, resultNotification);

        return result;
    }
}
