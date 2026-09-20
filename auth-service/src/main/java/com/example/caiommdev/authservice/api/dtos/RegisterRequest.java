package com.example.caiommdev.authservice.api.dtos;

public record RegisterRequest(
    String username,
    String password,
    String email
) {
}
