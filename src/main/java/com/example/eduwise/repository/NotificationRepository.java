package com.example.eduwise.repository;

import com.example.eduwise.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface    NotificationRepository  extends JpaRepository<Notification,Integer> {
}
