package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(schema = "driver",name = "orders")
@Entity

public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "payment_status")
    private  String paymentStatus;


    @ManyToOne
    private User user;
    @OneToMany
    private List<Course> courses;
}
