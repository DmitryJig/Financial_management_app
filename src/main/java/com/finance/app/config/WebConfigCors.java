package com.finance.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigCors {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // разрешить CORS для всех путей
                        .allowedOrigins("*") // разрешить запросы с любых доменов
                        .allowedMethods("*") // разрешить использовать любые методы запросов
                        .allowedHeaders("*"); // разрешить использовать любые заголовки запросов
            }
        };
    }
}
