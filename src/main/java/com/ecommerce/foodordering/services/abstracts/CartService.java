package com.ecommerce.foodordering.services.abstracts;

import com.ecommerce.foodordering.dtos.AddProductInCartDto;
import com.ecommerce.foodordering.dtos.OrderDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> addProductToCart (AddProductInCartDto addProductInCartDto);

    OrderDto getCartByUserId(Long userId);

    void deleteOrder(Long orderId);
}
