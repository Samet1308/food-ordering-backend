package com.ecommerce.foodordering.services.abstracts;

import com.ecommerce.foodordering.dtos.AddProductInCartDto;
import com.ecommerce.foodordering.dtos.OrderDto;
import com.ecommerce.foodordering.dtos.PlaceOrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    ResponseEntity<?> addProductToCart (AddProductInCartDto addProductInCartDto);

    OrderDto getCartByUserId(Long userId);

    void deleteOrder(Long orderId);

    OrderDto placeOrder(PlaceOrderDto placeOrderDto);

    List<OrderDto> getMyPlacedOrders(Long userId);
}
