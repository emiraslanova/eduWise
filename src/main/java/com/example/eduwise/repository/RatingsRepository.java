package com.example.eduwise.repository;

import com.example.eduwise.model.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings,Integer> {
}
