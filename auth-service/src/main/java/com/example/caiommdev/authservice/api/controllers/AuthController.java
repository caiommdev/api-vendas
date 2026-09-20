package com.example.caiommdev.authservice.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.caiommdev.authservice.api.dtos.LoginRequest;
import com.example.caiommdev.authservice.api.dtos.LoginResponse;
import com.example.caiommdev.authservice.api.dtos.RegisterRequest;
import com.example.caiommdev.authservice.api.dtos.UserResponse;
import com.example.caiommdev.authservice.application.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(request));
    }
}
