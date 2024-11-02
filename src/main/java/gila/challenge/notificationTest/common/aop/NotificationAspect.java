package gila.challenge.notificationTest.common.aop;

import gila.challenge.notificationTest.common.utilities.constants.NotificationConstants;
import gila.challenge.notificationTest.common.utilities.enums.NotificationStatus;
import gila.challenge.notificationTest.dto.NotificationDto;
import gila.challenge.notificationTest.service.MessageService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Aspect
@Component
public class NotificationAspect {

    private static final Logger logger = LoggerFactory.getLogger(NotificationAspect.class);
    private final MessageService messageService;

    public NotificationAspect(MessageService messageService) {
        this.messageService = messageService;
    }

    @AfterReturning(pointcut = "execution(* gila.challenge.notificationTest.service.Interfaces.Notification.sendNotification(..))", returning = "result")
    public void afterSendingNotification(Object result) {
        logger.info("NotificationAspect.afterSendingNotification: Logging notification status2");

        if (result instanceof CompletableFuture) {
            ((CompletableFuture<?>) result).thenAccept(response -> {
                if (response instanceof Map) {
                    Map<String, Object> responseMap = (Map<String, Object>) response;
                    boolean isResultPresent = Boolean.parseBoolean(responseMap.get(NotificationConstants.RESULT).toString());
                    String status = isResultPresent ? NotificationStatus.SENT.name() : NotificationStatus.FAILED.name();
                    messageService.saveMessage((NotificationDto) responseMap.get(NotificationConstants.NOTIFICATION), responseMap.get(NotificationConstants.CHANNEL_TYPE).toString(), status);
                }
            });
        }
    }

}
