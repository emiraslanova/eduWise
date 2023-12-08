package com.example.eduwise.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AuthenticationResponseDto {
    private String authToken;
}
