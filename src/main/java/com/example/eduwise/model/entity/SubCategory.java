package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(schema = "driver",name = "subCategory")
@Entity
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "subcategory_name")
    private String SubCategoryName;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private Category category;


}
