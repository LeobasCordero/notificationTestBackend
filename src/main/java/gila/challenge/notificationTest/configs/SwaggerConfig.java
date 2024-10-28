package gila.challenge.notificationTest.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Notification Test API")
                        .version("1.0")
                        .description("API for managing notifications through different channels")
                        .contact(new Contact()
                                .name("Leo Cordero")
                                .email("leocg619@hotmail.com"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("More about SpringDoc")
                        .url("https://springdoc.org/"));
    }
}