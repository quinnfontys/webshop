package com.qrdsn.fullstackbackend.repository;

import com.qrdsn.fullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
