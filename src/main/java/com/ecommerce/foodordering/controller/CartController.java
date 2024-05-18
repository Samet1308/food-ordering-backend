package com.ecommerce.foodordering.controller;

import com.ecommerce.foodordering.dtos.AddProductInCartDto;
import com.ecommerce.foodordering.dtos.OrderDto;
import com.ecommerce.foodordering.services.abstracts.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart (@RequestBody AddProductInCartDto addProductInCartDto){
        return cartService.addProductToCart(addProductInCartDto);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartByUserId (@PathVariable Long userId){
        OrderDto orderDto = cartService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @DeleteMapping("/cart/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId){
        cartService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
