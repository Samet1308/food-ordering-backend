package com.ecommerce.foodordering.services.abstracts;

import com.ecommerce.foodordering.dtos.requests.category.CategoryDTO;
import com.ecommerce.foodordering.dtos.requests.product.ProductDTO;

import java.util.List;

public interface CustomerService {
    List<CategoryDTO> getAllCategories();

    List<CategoryDTO> getCategoriesByName(String title);

    List<ProductDTO> getProductsByCategory(Long categoryId);
}
