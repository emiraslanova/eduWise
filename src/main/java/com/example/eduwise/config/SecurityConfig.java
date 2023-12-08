package com.example.eduwise.config;

import com.example.eduwise.component.AuthenticationFilter;
import com.example.eduwise.component.AuthorizationFilter;
import com.example.eduwise.component.LimitLoginAuthenticationProvider;
import com.example.eduwise.repository.UserRepository;
import com.example.eduwise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.Collections;

@Configuration
//@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private UserService userService;

    private AuthenticationEntryPoint entryPoint;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired

    public void setEntryPoint(AuthenticationEntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    @Autowired

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;


    @Value("${app.jwt.secret}")
    private String secret;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new LimitLoginAuthenticationProvider(passwordEncoder(), userService);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() {
        return new ProviderManager(Collections.singletonList(authenticationProvider()));
    }

    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter(authenticationManagerBean(), secret);
    }

    @Bean
    public AuthorizationFilter authorizationFilter() {

        return new AuthorizationFilter(authenticationManagerBean(), userRepository);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(a -> a
                        .requestMatchers("registration/**").permitAll()
                        .requestMatchers("api/v1/blog/**").permitAll()
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterAt(authenticationFilter(), BasicAuthenticationFilter.class)
                .userDetailsService(userService)
                .addFilterAt(authenticationFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(authorizationFilter(), AuthenticationFilter.class)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(configurer -> configurer.authenticationEntryPoint(entryPoint))
                .build();

    }


}


