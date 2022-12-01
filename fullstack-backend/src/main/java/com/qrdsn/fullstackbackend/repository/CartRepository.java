package com.qrdsn.fullstackbackend.repository;

import com.qrdsn.fullstackbackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
