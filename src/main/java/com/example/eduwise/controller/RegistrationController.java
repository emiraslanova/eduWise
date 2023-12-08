package com.example.eduwise.controller;

import com.example.eduwise.exceptions.UserException;
import com.example.eduwise.model.dto.*;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegistrationDto registrationDto) {
        registrationService.register(registrationDto);
    }

    @GetMapping("/resend")
    @PreAuthorize("PermitAll()")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void resend(@RequestParam String email) {
        registrationService.resend(email);
    }

    @GetMapping("/confirmation/{uuid}")
    protected void confirmation(@PathVariable UUID uuid) {
        registrationService.confirmation(uuid);


    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
        try {
            String authToken = String.valueOf(registrationService.login(loginDto.getUsername(), loginDto.getPassword()));
            AuthenticationResponseDto responseDto = new AuthenticationResponseDto(authToken);
            return ResponseEntity.ok(responseDto);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPassword(@RequestBody ForgetPasswordRequest request) {
        registrationService.forgetPassword(request);
        return ResponseEntity.ok("Password reset email sent successfully");
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestParam UUID resetUuid, @RequestParam String newPassword) {
        registrationService.resetPassword(resetUuid, newPassword);
        return ResponseEntity.ok("Password has been reset successfully");
    }


}
