package com.example.webserrvice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class WebserrviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebserrviceApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        WebMvcConfigurerAdapter webMvcConfigurerAdapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/user").allowedOrigins("http://localhost:4200");
                registry.addMapping("/userlist").allowedOrigins("http://localhost:4200");
                registry.addMapping("/login").allowedOrigins("http://localhost:4200");
            }
        };
        return webMvcConfigurerAdapter;
    }
}
