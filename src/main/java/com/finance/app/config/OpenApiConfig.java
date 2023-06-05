package com.finance.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Financial REST API",
                description = "Financial Management App", version = "1.0.0",
                contact = @Contact(
//                        name = "Klimtsov Konstantin",
//                        email = "https://t.me/konstantinprog",
                        url = "https://github.com/DmitryJig/Financial_management_app"
                )
        )
)
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "Authorization header with JWT token"
)
public class OpenApiConfig {

}
