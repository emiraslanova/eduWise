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

public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)



    @ManyToOne
    private  User user;

    @ManyToOne
    private Role role;
}
