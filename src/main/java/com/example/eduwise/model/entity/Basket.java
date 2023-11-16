package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(schema = "driver",name = "basket")
@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Column(name = "count")
    private int count;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "price")
    private  double price;


    @OneToMany
    private List <User> users;

//    @OneToOne
//    private  User user;
}
