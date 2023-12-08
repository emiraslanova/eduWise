package com.example.eduwise.component;

import com.example.eduwise.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {


    public LimitLoginAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService service) {
        setPasswordEncoder(passwordEncoder);
        setUserDetailsService(service);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            Authentication authenticated = super.authenticate(authentication);
            resetAttempts(authenticated.getName());
            return authenticated;
        } catch (BadCredentialsException exception) {
            increaseAttemptCount(authentication.getName());
            throw exception;
        }
    }

    private void resetAttempts(String username) {
        UserService userDetailsService = (UserService)getUserDetailsService();
        userDetailsService.resetAttempts(username);
    }

    private void increaseAttemptCount(String username) {
        UserService userDetailsService = (UserService)getUserDetailsService();
        userDetailsService.increaseAttemptCount(username);
    }

}
