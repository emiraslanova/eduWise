package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(schema = "driver",name = "confirmationToken")
@Entity

public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;

    @Column(name = "token")
    private String token;

    @Column(name = "confirmed_at")
    private String confirmedAt;

    @Column(name = "confirm")
    private String confirm;


    @OneToOne
    private User user;
}
