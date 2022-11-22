package com.qrdsn.fullstackbackend.repository;

import com.qrdsn.fullstackbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRespository extends JpaRepository<Product, Long> {
//    Optional<List<Product>> findByCategoryId(Long category_id);
}