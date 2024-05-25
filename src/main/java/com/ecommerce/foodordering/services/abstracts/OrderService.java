package com.ecommerce.foodordering.services.abstracts;

import com.ecommerce.foodordering.dtos.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllPlacedOrders();
}
