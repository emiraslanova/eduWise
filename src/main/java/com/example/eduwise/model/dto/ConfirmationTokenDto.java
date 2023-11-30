package com.example.eduwise.model.dto;

;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfirmationTokenDto {

    private String token;

    private String confirmedAt;

    private String confirm;


}
