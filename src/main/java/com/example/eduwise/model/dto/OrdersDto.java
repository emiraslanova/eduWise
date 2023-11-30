package com.example.eduwise.model.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersDto {

    private LocalDate orderDate;

    private int quantity;

    private double totalAmount;

    private  String paymentStatus;
}
