package com.findserv.root.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("Service Processor API")
                .version("1.0")
                .description("API for managing services and customers.");

        // Define security scheme for JWT
        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .info(info)
                .components(new Components().addSecuritySchemes("bearer-key", bearerAuth))
                .addSecurityItem(new SecurityRequirement().addList("bearer-key")); // Apply globally
    }
}
