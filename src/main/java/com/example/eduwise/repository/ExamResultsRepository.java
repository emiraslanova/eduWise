package com.example.eduwise.repository;

import com.example.eduwise.model.entity.ExamResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultsRepository extends JpaRepository<ExamResults,Integer> {

}
