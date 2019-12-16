package com.negotium.negotiumapp.controller.web;

import com.negotium.negotiumapp.domain.User;
import com.negotium.negotiumapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
public class UserController {

//    private UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @RequestMapping({"/done"})
    public String firstPage() {
        return "I'm alive!! ;) ";
    }


    /*
    public static final String REST_USER = "/rest/user";
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED) //TODO: Zmienić
    public String loginUser(@RequestBody final User user){
      //Tutaj sprawdzimy czy użytkownik istenieje i czy jego dane zgadzaja się w bazie danych
        return getLoginUserString(user);
    }

    private String getLoginUserString(@RequestBody User user) {
        return userService.loginUser(user);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        return getRegisterUserString(user, result, model);
    }

    private String getRegisterUserString(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        boolean isAdded = userService.registerUser(user);

        if (isAdded) {
            model.addAttribute("message", "Użytkownik" + user.getUsername() + " dodany");
        } else if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            model.addAttribute("message", "Dodanie użytkownika nie powiodło się");
            return "/register_form";
        }
        return "index";
    }
    /
     */


}

