package gila.challenge.notificationTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync(proxyTargetClass = true)
@EnableAspectJAutoProxy
@SpringBootApplication
public class NotificationTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationTestApplication.class, args);
	}

}
