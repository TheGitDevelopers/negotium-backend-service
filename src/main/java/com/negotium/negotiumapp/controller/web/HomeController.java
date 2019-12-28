package com.negotium.negotiumapp.controller.web;

import com.negotium.negotiumapp.model.user.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register_form")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "register_form";
    }

    @GetMapping("/login_form")
    public String login() {
        return "login_form";
    }

}
