package com.ecommerce.foodordering.controller;

import com.ecommerce.foodordering.dtos.requests.category.CategoryDTO;
import com.ecommerce.foodordering.services.abstracts.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> postCategory(@ModelAttribute CategoryDTO categoryDTO) throws IOException {
        CategoryDTO createdCategoryDTO = categoryService.postCategory(categoryDTO);
        if(createdCategoryDTO==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(createdCategoryDTO);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> categoryDtoList = categoryService.getAllCategories();
        if(categoryDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }
}
