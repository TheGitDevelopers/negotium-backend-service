package com.negotium.negotiumapp.controller.web;

import com.negotium.negotiumapp.model.User;

import com.negotium.negotiumapp.repository.UserRepository;
import com.negotium.negotiumapp.service.JwtUserDetailsService;
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
    public UserController(UserRepository userRepository, UserService userService){
        this.userRepository =  userRepository;
        this.userService = userService;
    }

    @PostMapping("/register")
    public String addWithDefaultRole(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        boolean isAdded = userService.addWithDefaultRole(user);
        if (isAdded) {
            model.addAttribute("message", "User " + user.getUsername() + " added");
        } else if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            return "register_form";
        } else {
            List<User> users = userRepository.findAll();
            for (User e : users) {
                if (e.getUsername().equals(user.getUsername())) {
                    model.addAttribute("message", "The user is already exists");
                } else if (user.getEmail().equals(user.getEmail())) {
                    model.addAttribute("message", "Email address is already exist");
                } else {
                    model.addAttribute("message", "Adding user failed, please try again later");
                }
            }
        }
        return "register_form";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute String username, String password){
        JwtUserDetailsService jwtUserDetailsService = new JwtUserDetailsService();
        User user = userRepository.findByUsername(username);
        if(password.equals(user.getPassword())) {
            jwtUserDetailsService.loadUserByUsername(username);
        }
        return "index";
    }

}

