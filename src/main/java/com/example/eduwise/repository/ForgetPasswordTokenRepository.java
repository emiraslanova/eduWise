package com.example.eduwise.repository;

import com.example.eduwise.model.entity.ForgetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgetPasswordTokenRepository extends JpaRepository<ForgetPasswordToken,Integer> {
}
