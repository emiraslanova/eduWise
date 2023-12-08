package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(schema = "driver",name = "role")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;

    @Column(name = "name")
    private String name ;


    @ManyToMany
    @JoinTable(schema = "driver",
            name = "users_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )

    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER )
    private List<Permission> permission = new ArrayList<>();

}
