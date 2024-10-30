package gila.challenge.notificationTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
public class NotificationTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationTestApplication.class, args);
	}

}
