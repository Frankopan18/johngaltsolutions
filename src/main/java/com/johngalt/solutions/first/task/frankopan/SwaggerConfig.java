package com.johngalt.solutions.first.task.frankopan;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("John Galt API Documentation")
                        .version("1.0")
                        .description("This is the API documentation for the assessment task."));
    }
}