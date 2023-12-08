package com.example.eduwise.repository;

import com.example.eduwise.model.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics,Integer> {
}
