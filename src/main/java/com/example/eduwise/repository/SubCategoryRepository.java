package com.example.eduwise.repository;

import com.example.eduwise.model.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  SubCategoryRepository  extends JpaRepository<SubCategory ,Integer> {
}
