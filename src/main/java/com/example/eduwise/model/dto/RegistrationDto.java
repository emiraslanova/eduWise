package com.example.eduwise.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationDto {

    private  String username;

    private  String password;

    private  String confirmPassword;


}
