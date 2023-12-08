package com.example.eduwise.repository;

import com.example.eduwise.model.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog ,Integer> {

}
