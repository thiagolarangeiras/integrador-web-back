package com.github.integrador;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenApiCustomizer securityOpenApiCustomiser() {
        return openApi -> {
            SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearer-key");
            openApi.getPaths().values().forEach(pathItem ->
                    pathItem.readOperations().forEach(operation ->
                            operation.addSecurityItem(securityRequirement)
                    )
            );
        };
    }
}