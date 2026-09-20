package com.example.caiommdev.authservice.api.dtos;

public record LoginResponse(
    String token,
    String tokenType
) {
    
}
