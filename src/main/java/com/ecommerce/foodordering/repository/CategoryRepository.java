package com.ecommerce.foodordering.repository;

import com.ecommerce.foodordering.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findAllByNameContaining(String title);


}
