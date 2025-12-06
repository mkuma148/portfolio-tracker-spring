package com.crypto.tracker.controller;

import org.springframework.web.bind.annotation.*;
import com.crypto.tracker.model.User;
import com.crypto.tracker.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public User signup(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        return userService.signup(username, password, email);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        boolean success = userService.login(username, password);
        return success ? "Login successful" : "Invalid username/password";
    }
}