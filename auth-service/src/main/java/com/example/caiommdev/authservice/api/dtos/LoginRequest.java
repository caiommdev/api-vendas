package com.example.caiommdev.authservice.api.dtos;

public record LoginRequest(
    String email,
    String password
) {
}
