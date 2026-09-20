package com.example.caiommdev.authservice.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.caiommdev.authservice.api.dtos.UserResponse;
import com.example.caiommdev.authservice.api.dtos.UpdateUserRequest;
import com.example.caiommdev.authservice.domain.models.User;
import com.example.caiommdev.authservice.infrastructure.repositories.UserRepository;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(this::toUserResponse).toList();
    }

    public Optional<UserResponse> findById(UUID userId) {
        return userRepository.findById(userId)
                .map(this::toUserResponse);
    }

    public Optional<UserResponse> update(UUID userId, UpdateUserRequest request) {
        return userRepository.findById(userId).map(user -> {
            if (!user.getUsername().equals(request.username())
                    && userRepository.existsByUsername(request.username()))
                throw new IllegalStateException("Username já está em uso: " + request.username());
            if (!user.getEmail().equals(request.email())
                    && userRepository.existsByEmail(request.email()))
                throw new IllegalStateException("E-mail já está em uso: " + request.email());

            user.setUsername(request.username());
            user.setEmail(request.email());
            return toUserResponse(userRepository.save(user));
        });
    }

    public void deleteById(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalStateException("Usuário não encontrado com ID: " + userId);
        }
        userRepository.deleteById(userId);
    }

    private UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}
