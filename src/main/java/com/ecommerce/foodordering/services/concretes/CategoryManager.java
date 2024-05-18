package com.ecommerce.foodordering.services.concretes;

import com.ecommerce.foodordering.dtos.CategoryDTO;
import com.ecommerce.foodordering.dtos.ProductDTO;
import com.ecommerce.foodordering.entities.Category;
import com.ecommerce.foodordering.entities.Product;
import com.ecommerce.foodordering.repository.CategoryRepository;
import com.ecommerce.foodordering.repository.ProductRepository;
import com.ecommerce.foodordering.services.abstracts.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;


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

    @Override
    public List<CategoryDTO> getAllCategoriesByTitle(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }



    @Override
    public ProductDTO postProduct(Long categoryId, ProductDTO productDto) throws IOException {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent()){
            Product product = new Product();
            BeanUtils.copyProperties(productDto,product);
            product.setImg(productDto.getImg().getBytes());
            product.setCategory(optionalCategory.get());
            Product createdProduct = productRepository.save(product);
            ProductDTO createdProductDto = new ProductDTO();
            createdProductDto.setId(createdProduct.getId());
            return createdProductDto;
        }

        return null;
    }

    @Override
    public List<ProductDTO> getAllProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }
    @Override
    public List<ProductDTO> getAllProductsByCategoryAndByTitle(Long categoryId,String title) {
        return productRepository.findAllByCategoryIdAndNameContaining(categoryId, title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            productRepository.deleteById(productId);
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent()){
            categoryRepository.deleteById(categoryId);
        }
    }
}
