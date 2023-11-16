package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(schema = "driver",name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(unique = true)
    @Pattern(regexp = "[\\w.-]+@[\\w.-]+.\\w+$")
    private String email;

    @Pattern(regexp = "[0-9]{3}+[0-9]{3}+[0-9]{2}+[0-9]{2}")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "birth-date")
    private LocalDate birthdate;

    @Column(name = "registration_date")
    private  LocalDate registrationDate;

    @ManyToMany
    @JoinTable(schema = "driver",
            name = "user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )

    private Set<Course> courseset;


    @OneToMany(mappedBy = "user")
    private List<Certificate> certificates;


    @OneToMany
    private List<Exam>exams;
    //exam
    @ManyToOne
    private ExamResults examResults;
    //exam result




 //   @ManyToMany
 //   @JoinTable
    //course


    //certifcate
















//    user_id,
//    username,
//    email,
//    password,
//    role,
//    registration_date

}
