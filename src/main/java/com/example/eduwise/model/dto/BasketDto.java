package com.example.eduwise.model.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketDto {

    private int count;

    private double totalAmount;

    private  double price;



}
