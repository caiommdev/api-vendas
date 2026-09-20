package com.example.caiommdev.authservice.infrastructure.security;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import com.example.caiommdev.authservice.domain.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;  

@Service
public class JwtService {

    private SecretKey secretKey;
    private final long expirationMS;

    public JwtService(@Value("${jwt.secret}") String secret, 
                      @Value("${jwt.expiration-ms}") long expirationMS) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMS = expirationMS;
    }

    public String generateToken(User user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId().toString())
                .claim("email", user.getEmail())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(expirationMS)))
                .signWith(secretKey)
                .compact();
    }
}
