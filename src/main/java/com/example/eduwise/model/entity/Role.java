package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(schema = "driver",name = "statistics")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;

    @Column(name = "name")
    private String name ;


    @ManyToMany
    @JoinTable(schema = "Driver",
            name = "Role",
            joinColumns = @JoinColumn(name = "Role_id"),
            inverseJoinColumns = @JoinColumn(name = "User")
    )

    private Set<User> users;


}
