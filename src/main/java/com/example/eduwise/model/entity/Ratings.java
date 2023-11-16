package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table
@Entity

public class Ratings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Column(name = "rating")
    private  int rating;

    @Column(name = "review")
    private double review;


    @ManyToOne
    private  User user;

    @ManyToOne
    private  Course course;

}
