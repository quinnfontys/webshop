package com.qrdsn.fullstackbackend.repository;

import com.qrdsn.fullstackbackend.model.Cart;
import com.qrdsn.fullstackbackend.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    CartProduct findByCartIdAndProductId(Long cartId, Long productId);
}