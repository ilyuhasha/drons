package com.example.demo.controller;

import com.example.demo.models.Users;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUsernameById(@PathVariable Long id) {
        Optional<Users> userOptional = userService.getUserById(id);
        return userOptional.map(user -> ResponseEntity.ok(user.getUsername()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/idByUsername/{username}")
    public ResponseEntity<Long> getIdByUsername(@PathVariable String username) {
        Optional<Long> userIdOptional = userService.findIdByUsername(username);
        return userIdOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<String> getAddressByUserId(@PathVariable Long id) {
        Optional<Users> userOptional = userService.getUserById(id);
        return userOptional.map(user -> ResponseEntity.ok(user.getAddress()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<String> getRoleByUserId(@PathVariable Long id) {
        Optional<Users> userOptional = userService.getUserById(id);
        return userOptional.map(user -> ResponseEntity.ok(user.getRole().toString()))
                .orElse(ResponseEntity.notFound().build());
    }
}
