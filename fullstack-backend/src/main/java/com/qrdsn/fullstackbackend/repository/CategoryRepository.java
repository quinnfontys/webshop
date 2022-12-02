package com.qrdsn.fullstackbackend.repository;

import com.qrdsn.fullstackbackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
