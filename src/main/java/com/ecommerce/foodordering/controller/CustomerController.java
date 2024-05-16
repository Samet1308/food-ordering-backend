package com.ecommerce.foodordering.controller;

import com.ecommerce.foodordering.dtos.requests.category.CategoryDTO;
import com.ecommerce.foodordering.dtos.requests.product.ProductDTO;
import com.ecommerce.foodordering.services.abstracts.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> categoryDtoList = customerService.getAllCategories();
        if(categoryDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByName(@PathVariable String title){
        List<CategoryDTO> categoryDtoList = customerService.getCategoriesByName(title);
        if(categoryDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable Long categoryId){
        List<ProductDTO> productDtoList = customerService.getProductsByCategory(categoryId);
        if(productDtoList==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }
}
