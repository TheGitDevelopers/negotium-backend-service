package com.negotium.negotiumapp;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

@SpringBootApplication
@RestController
public class NegotiumappApplication {

    private static final Logger logger = LoggerFactory.getLogger(NegotiumappApplication.class);


    public static void main(String[] args)
    {
        SpringApplication.run(NegotiumappApplication.class, args);


    }

}
