package com.example.eduwise.model.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDto {

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String correctOption;

}
