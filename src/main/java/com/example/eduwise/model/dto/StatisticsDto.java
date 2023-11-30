package com.example.eduwise.model.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsDto {

    private String name;

    private String description;

    private double totalBudget;
}
