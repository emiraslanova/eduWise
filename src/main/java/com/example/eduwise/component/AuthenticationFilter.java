package com.example.eduwise.component;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.eduwise.model.dto.AuthenticationResponseDto;
import com.example.eduwise.model.dto.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
@Data
@ToString
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${app.jwt.secret}")
    private final String secret;
    private String generatedToken;  // JWT'yi saklamak için bir değişken ekledik

    // Diğer metotlar

    public String getGeneratedToken() {
        return generatedToken;
    }


    public AuthenticationFilter(AuthenticationManager manager, String secret) {
        this.secret = secret;
        setAuthenticationManager(manager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            LoginDto dto = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
            UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken
                    .authenticated(dto.getUsername(),
                            dto.getPassword(),
                            Collections.emptyList());
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        generatedToken = JWT.create()
                 .withSubject(authResult.getName())
                 .withExpiresAt(new Date(System.currentTimeMillis() + Duration.ofMinutes(10).toMillis()))
                 .withIssuedAt(new Date())
                 .sign(Algorithm.HMAC256(secret.getBytes()));

        response.getWriter().write(new ObjectMapper().writeValueAsString(new AuthenticationResponseDto(generatedToken)));
    }
}




