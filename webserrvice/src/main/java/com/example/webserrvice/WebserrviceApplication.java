package com.example.webserrvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class WebserrviceApplication {

    @Autowired
    WebApiController controller;

    public static void main(String[] args) {
        SpringApplication.run(WebserrviceApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/*")
                        .allowedOrigins("http://localhost:8080")
                        .allowedMethods("GET", "PUT", "DELETE")
                        .allowCredentials(false).maxAge(3600);
            }
        };
    }
}
