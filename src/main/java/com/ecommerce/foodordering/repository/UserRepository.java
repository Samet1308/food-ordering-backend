package com.ecommerce.foodordering.repository;

import com.ecommerce.foodordering.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findFirstByEmail(String email);
}
