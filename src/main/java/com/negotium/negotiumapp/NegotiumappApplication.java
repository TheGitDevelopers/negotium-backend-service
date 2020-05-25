package com.negotium.negotiumapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {"com.negotium.negotiumapp"})
@RestController
public class NegotiumappApplication {

    private static final Logger logger = LoggerFactory.getLogger(NegotiumappApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(NegotiumappApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/register")
                        .allowedOrigins("http://localhost:4200");
            }
        };
    }

}
