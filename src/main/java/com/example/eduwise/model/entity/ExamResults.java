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
@Table(schema = "driver",name = "examResults")
@Entity
public class ExamResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "obtained_marks")
    private  String obtainedMarks;

    @Column(name = "total_marks")
    private String totalMarks;

    @Column(name = "exam_date")
    private LocalDate examDate;


    @OneToMany
    private List<User> users;

    @ManyToOne
    private  Exam exam;
}
