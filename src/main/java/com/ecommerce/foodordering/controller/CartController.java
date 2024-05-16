package com.ecommerce.foodordering.controller;

import com.ecommerce.foodordering.dtos.responses.product.AddProductInCartDto;
import com.ecommerce.foodordering.services.abstracts.CartService;
import lombok.RequiredArgsConstructor;
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
}
