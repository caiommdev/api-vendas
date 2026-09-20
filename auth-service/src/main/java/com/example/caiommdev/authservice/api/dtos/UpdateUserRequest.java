package com.example.caiommdev.authservice.api.dtos;

public record UpdateUserRequest (
    String username, 
    String email
) {
    
}
