package com.pgoncharova.taxfiling.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Tax Filing API", version = "1.0", description = "API for managing tax filing"))
public class SwaggerConfig {
}
