package com.nutrimedica.nutrimedica_api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nutrimedica.nutrimedica_api.dto.User;
import com.nutrimedica.nutrimedica_api.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody @Valid User user) {
        userService.createUser(user);
        return "User created successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }
}
