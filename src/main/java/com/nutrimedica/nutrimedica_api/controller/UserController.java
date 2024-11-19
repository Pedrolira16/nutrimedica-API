package com.nutrimedica.nutrimedica_api.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nutrimedica.nutrimedica_api.dto.User;
import com.nutrimedica.nutrimedica_api.service.UserService;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import com.nutrimedica.nutrimedica_api.utils.JwtUtil;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String createUser(@RequestBody @Valid User user) {
        userService.createUser(user);
        return "User created successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/userDetail")
    public User getUserDetail(HttpServletRequest request) {
        String token = JwtUtil.extractToken(request);

        if (token == null) {
            throw new IllegalArgumentException("Unauthorized: Invalid token.");
        }

        Long userId = JwtUtil.extractUserId(token);

        if (userId == null) {
            throw new IllegalArgumentException("Unauthorized: Invalid token.");
        }

        return userService.getUserDetail(userId);
    }

    @GetMapping("/doctors")
    public List<User> getUsersDoctors() {
        return userService.getUsersDoctors();
    }

    @GetMapping("/receptionists")
    public List<User> getUsersReceptionists() {
        return userService.getUsersReceptionists();
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully!";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return "User updated successfully!";
    }
}
