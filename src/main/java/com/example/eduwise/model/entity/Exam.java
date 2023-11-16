package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(schema = "driver",name = "exam")
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "exam_date")
    private LocalDate examDate;

    @Column(name = "duration_minutes")
    private double durationMinutes;

    @Column(name = "total_marks")
    private  double totalMarks;

    @Column(name = "description")
    private  String description;

    @ManyToOne
    private  Course course;
}
