package com.ecommerce.foodordering.controller;

import com.ecommerce.foodordering.dtos.requests.category.CategoryDTO;
import com.ecommerce.foodordering.dtos.requests.product.ProductDTO;
import com.ecommerce.foodordering.services.abstracts.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/categories{title}")
    public ResponseEntity<List<CategoryDTO>> getAllCategoriesByTitle(@PathVariable String title){
        List<CategoryDTO> categoryDtoList = categoryService.getAllCategoriesByTitle(title);
        if(categoryDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @PostMapping("/{categoryId}/product")
    public ResponseEntity<?> postProduct(@PathVariable Long categoryId ,@ModelAttribute ProductDTO productDto) throws IOException {
        ProductDTO createdproductDTO = categoryService.postProduct(categoryId, productDto);
        if(createdproductDTO==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdproductDTO);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDTO>> getAllProductsByCategory(@PathVariable Long categoryId){
        List<ProductDTO> productDtoList = categoryService.getAllProductsByCategory(categoryId);
        if(productDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

}
