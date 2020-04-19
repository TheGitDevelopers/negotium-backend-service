package com.negotium.negotiumapp.controller;

import com.negotium.negotiumapp.model.user.UserDto;
import com.negotium.negotiumapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.negotium.negotiumapp.security.SecurityConstans.USER_URL;

@RestController
@RequestMapping(USER_URL)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addWithDefaultRole(@RequestBody UserDto user, Model model) {
        boolean isAdded = userService.addWithDefaultRole(user);
        if (!isAdded) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User registration has failed");
        } else {
            model.addAttribute("message", "User " + user.getUsername() + " added");
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/findAll", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> findAll(@RequestParam(required = false) String username) {
        if (username != null) {
            return userService.findAllByUsername(username);
        }
        return userService.findAll();
    }

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}