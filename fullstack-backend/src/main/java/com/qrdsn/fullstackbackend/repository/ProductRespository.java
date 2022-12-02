package com.qrdsn.fullstackbackend.repository;

import com.qrdsn.fullstackbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRespository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long categoryId);
}