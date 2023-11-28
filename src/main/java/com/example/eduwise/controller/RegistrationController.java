package com.example.eduwise.controller;

import com.example.eduwise.model.dto.RegistrationDto;
import com.example.eduwise.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private  final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegistrationDto registrationDto){
        registrationService.register(registrationDto);
    }

    @GetMapping("/confirmation/{uuid}")

    protected void  confirmation(@PathVariable UUID uuid){
        registrationService.confirmation(uuid);

    }
}
