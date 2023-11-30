package com.example.eduwise.repository;

import com.example.eduwise.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUuid(UUID uuid);

}
