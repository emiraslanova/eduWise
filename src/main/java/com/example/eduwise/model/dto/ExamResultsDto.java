package com.example.eduwise.model.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamResultsDto {

    private  String obtainedMarks;

    private String totalMarks;

    private LocalDate examDate;

}
