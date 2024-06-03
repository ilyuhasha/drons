package com.example.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

// AuthResponse.java
@Data
@AllArgsConstructor
public class AuthResponse {
    private String jwt;
    private Long userId;
}
