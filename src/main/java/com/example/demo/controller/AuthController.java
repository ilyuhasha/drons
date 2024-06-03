package com.example.demo.controller;

import com.example.demo.jwt.JwtUtil;
import com.example.demo.models.Users;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

// AuthController.java
@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        Users savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        // Получаем идентификатор пользователя по его имени
        Optional<Long> userIdOptional = userService.findIdByUsername(authRequest.getUsername());
        Long userId = userIdOptional.orElse(null);



        // Получаем детали пользователя
        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());

        // Генерируем JWT для пользователя
        final String jwt = jwtUtil.generateToken(userDetails);

        // Возвращаем JWT и идентификатор пользователя в ответе
        return ResponseEntity.ok(new AuthResponse(jwt, userId));
    }

}




