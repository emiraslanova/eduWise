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
@Table(schema = "driver", name = "certificate")
@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "issue_date")
    private LocalDate issueDate;


//    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumns({
//            @JoinColumn(name = "user_id", referencedColumnName = "id")
//
//    })
//    private User user;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    private Course course;
    //user id
    //course id

}
