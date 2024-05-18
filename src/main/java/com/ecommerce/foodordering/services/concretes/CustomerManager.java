package com.ecommerce.foodordering.services.concretes;

import com.ecommerce.foodordering.dtos.CategoryDTO;
import com.ecommerce.foodordering.dtos.ProductDTO;
import com.ecommerce.foodordering.entities.Category;
import com.ecommerce.foodordering.entities.Product;
import com.ecommerce.foodordering.repository.CategoryRepository;
import com.ecommerce.foodordering.repository.ProductRepository;
import com.ecommerce.foodordering.services.abstracts.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getCategoriesByName(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }
}
