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
public class ForgetPasswordToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "token")
    private String token;

    @Column(name = "confirmed_at")
    private String confirmedAt;

    @Column(name = "confirm")
    private String confirm;

    @OneToOne
    private User user;
}
