package com.example.eduwise.model.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamDto {

    private String examName;

    private LocalDate examDate;

    private double durationMinutes;

    private  double totalMarks;

    private  String description;
}
