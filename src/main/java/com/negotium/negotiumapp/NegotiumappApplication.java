package com.negotium.negotiumapp;

import com.negotium.negotiumapp.security.jwt.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NegotiumappApplication {

    public static void main(String[] args) {
        SpringApplication.run(NegotiumappApplication.class, args);
    }

}
