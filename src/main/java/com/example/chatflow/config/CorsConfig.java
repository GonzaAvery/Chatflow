package com.example.chatflow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permitir solicitudes desde el front-end en localhost:5173
        registry.addMapping("/**")  // Aplica a todos los endpoints REST
                .allowedOrigins("http://localhost:5173")  // Permitir solicitudes desde tu front-end
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos HTTP permitidos
                .allowedHeaders("*");  // Permitir todos los encabezados
    }
}