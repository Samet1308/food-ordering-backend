package com.ecommerce.foodordering.services.abstracts;

import com.ecommerce.foodordering.dtos.CategoryDTO;
import com.ecommerce.foodordering.dtos.ProductDTO;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    CategoryDTO postCategory(CategoryDTO categoryDTO) throws IOException;

    List<CategoryDTO> getAllCategories();

    List<CategoryDTO> getAllCategoriesByTitle(String title);

    ProductDTO postProduct(Long categoryId, ProductDTO productDto) throws IOException;

    List<ProductDTO> getAllProductsByCategory(Long categoryId);

    List<ProductDTO> getAllProductsByCategoryAndByTitle(Long categoryId, String title);

    void deleteProduct(Long productId);

    void deleteCategory(Long categoryId);
}
