package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserIdController {
    private final UserService userService;

    public UserIdController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userid")
    public ResponseEntity<Long> getUserId(@RequestParam String username) {
        Long userId = userService.findIdByUsername(username).orElse(null);
        return ResponseEntity.ok(userId);

    }
}

