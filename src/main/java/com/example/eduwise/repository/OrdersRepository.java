package com.example.eduwise.repository;

import com.example.eduwise.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository  extends JpaRepository<Orders,Integer> {
}
