package com.example.webserrvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebserrviceApplication {

    @Autowired
    WebApiController controller;

    public static void main(String[] args) {
        SpringApplication.run(WebserrviceApplication.class, args);
    }

}
