package com.negotium.negotiumapp.controller.web;

import com.negotium.negotiumapp.model.user.UserDto;
import com.negotium.negotiumapp.repository.UserRepository;
import com.negotium.negotiumapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/register")
    public String addWithDefaultRole(@Valid @ModelAttribute UserDto user, BindingResult result, Model model) {
        boolean isAdded = userService.addWithDefaultRole(user);
        if (isAdded) {
            model.addAttribute("message", "User " + user.getUsername() + " added");
        } else if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            return "/register_form";
        } else {
            List<UserDto> users = userService.findAll();
            for (UserDto e : users) {
                if (e.getUsername().equals(user.getUsername())) {
                    model.addAttribute("message", "The user is already exists");
                } else if (user.getEmail().equals(user.getEmail())) {
                    model.addAttribute("message", "Email address is already exist");
                } else {
                    model.addAttribute("message", "Adding user failed, please try again later");
                }
            }
        }
        return "/";
    }

    @PostMapping("/login")
    public String login(@RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password) {
        System.out.println(password);
        userService.loginUser(username, password);
        return "/index";
    }

}

