package com.ecommerce.foodordering.services.concretes;

import com.ecommerce.foodordering.dtos.requests.category.CategoryDTO;
import com.ecommerce.foodordering.entities.Category;
import com.ecommerce.foodordering.repository.CategoryRepository;
import com.ecommerce.foodordering.services.abstracts.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public CategoryDTO postCategory(CategoryDTO categoryDTO) throws IOException {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setImg(categoryDTO.getImg().getBytes());

        Category createdCategory = categoryRepository.save(category);
        CategoryDTO createdCategoryDto = new CategoryDTO();
        createdCategoryDto.setId(createdCategory.getId());
        return createdCategoryDto ;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }
}
