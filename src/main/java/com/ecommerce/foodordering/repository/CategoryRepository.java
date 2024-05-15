package com.ecommerce.foodordering.repository;

import com.ecommerce.foodordering.dtos.requests.product.ProductDTO;
import com.ecommerce.foodordering.entities.Category;
import com.ecommerce.foodordering.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findAllByNameContaining(String title);


}
