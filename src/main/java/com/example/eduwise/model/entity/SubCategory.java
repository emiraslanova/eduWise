package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table
@Entity
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SubCategoryId;

    @Column(name = "subcategory_name")
    private String SubCategoryName;

    @Column(name = "desc")
    private String description;

    @ManyToOne
    private Category category;


}
