package com.example.bid.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {
//
//    @Bean
//    public OpenAPI baseOpenAPI() {
//        return new OpenAPI()
//                .components(new Components())
//                .info(new Info()
//                        .title("Spring boot Swagger Project OpenAPI Docs")
//                        .version("1.0.0")
//                        .description("API documentation for the Spring Boot project"))
//                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement()
//                        .addList("bearerAuth")); // Add the security requirement
//    }
}
