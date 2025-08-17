package com.findserv.root.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Create the main Info object without contact or license
        Info info = new Info()
                .title("Service Processor API")
                .version("1.0")
                .description("API for managing services and customers.");

        return new OpenAPI().info(info);
    }
}
