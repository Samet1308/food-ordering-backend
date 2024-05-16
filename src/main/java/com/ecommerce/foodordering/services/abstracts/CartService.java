package com.ecommerce.foodordering.services.abstracts;

import com.ecommerce.foodordering.dtos.responses.product.AddProductInCartDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> addProductToCart (AddProductInCartDto addProductInCartDto);
}
