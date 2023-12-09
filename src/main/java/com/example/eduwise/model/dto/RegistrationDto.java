package com.example.eduwise.model.dto;

import com.example.eduwise.enums.Role;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationDto {


    @Column(unique = true)
    private String username;

    private String password;

    private String confirmPassword;
    private Role role;


}
