package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(schema = "driver",name = "user")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(unique = true)
    @Pattern(regexp = "[\\w.-]+@[\\w.-]+.\\w+$")
    private String email;

    @Pattern(regexp = "[0-9]{3}+[0-9]{3}+[0-9]{2}+[0-9]{2}")
    private String phoneNumber;

   // @Pattern(regexp = "A-Za-z0-9 ")
    // "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
   // @Min(8)
    @Column(name = "password")
    private String password;

    @Column(name = "birth-date")
    private LocalDate birthdate;

    @Column(name = "registration_date")
    private  LocalDate registrationDate;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

   @Column(name = "credentials_non_expired")
    private  boolean credentialsNonExpired;

   @Column(name = "enabled")
    private boolean enabled;

   @Column(name = "username")
    private String username;

   @Column(name = "uuid")
    private UUID uuid;





    @ManyToMany
    @JoinTable(schema = "driver",
            name = "user_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )


    private Set<Course> courseset;


    @OneToMany(mappedBy = "user")
    private List<Certificate> certificates;


    @OneToMany
    private List<Exam>exams;

    @ManyToOne
    private ExamResults examResults;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }




















}
