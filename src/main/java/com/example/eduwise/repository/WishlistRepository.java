package com.example.eduwise.repository;

import com.example.eduwise.model.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {
}
