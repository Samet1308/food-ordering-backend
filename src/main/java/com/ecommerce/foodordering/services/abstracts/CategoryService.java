package com.ecommerce.foodordering.services.abstracts;

import com.ecommerce.foodordering.dtos.requests.category.CategoryDTO;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    CategoryDTO postCategory(CategoryDTO categoryDTO) throws IOException;

    List<CategoryDTO> getAllCategories();
}
