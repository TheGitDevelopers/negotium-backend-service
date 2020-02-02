package com.negotium.negotiumapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.negotium.negotiumapp.security.SecurityConstans.API_URL;

@RestController
@RequestMapping(API_URL)
public class HomeController {

    @GetMapping
    public String home() {
        return "/";
    }
}