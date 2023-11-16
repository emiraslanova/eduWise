package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table
@Entity
public class Certificate {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id ;

    @Column(name = "issue_date")
    private LocalDate issueDate;


    @ManyToOne
    private User user;

    @ManyToOne
    private  Course course;
    //user id
    //course id

}
