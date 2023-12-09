package com.example.eduwise.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt.secret}")
    private String secret;

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + Duration.ofMinutes(10).toMillis()))
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(secret.getBytes()));
    }

}
