package com.example.caiommdev.authservice.application;

import lombok.RequiredArgsConstructor;
import com.example.caiommdev.authservice.api.dtos.LoginRequest;
import com.example.caiommdev.authservice.api.dtos.LoginResponse;
import com.example.caiommdev.authservice.api.dtos.RegisterRequest;
import com.example.caiommdev.authservice.api.dtos.UserResponse;
import com.example.caiommdev.authservice.domain.models.User;
import com.example.caiommdev.authservice.infrastructure.repositories.UserRepository;
import com.example.caiommdev.authservice.infrastructure.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserResponse register(RegisterRequest request) {
        
        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalStateException("Username já está em uso: " + request.username());
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalStateException("E-mail já está em uso: " + request.email());
        }


        User user = User.builder()
                .username(request.username())
                .passwordHash(passwordEncoder.encode(request.password()))
                .email(request.email())
                .build();

        User savedUser = userRepository.save(user);
        return toUserResponse(savedUser);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("E-mail ou senha inválidos"));

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new IllegalArgumentException("E-mail ou senha inválidos");
        }

        String token = jwtService.generateToken(user);
        return new LoginResponse(token, "Bearer");
    }

    private UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}
