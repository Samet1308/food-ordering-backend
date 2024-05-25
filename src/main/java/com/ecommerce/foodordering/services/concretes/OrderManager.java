package com.ecommerce.foodordering.services.concretes;

import com.ecommerce.foodordering.dtos.OrderDto;
import com.ecommerce.foodordering.entities.Order;
import com.ecommerce.foodordering.enums.OrderStatus;
import com.ecommerce.foodordering.repository.OrderRepository;
import com.ecommerce.foodordering.services.abstracts.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderManager implements OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDto> getAllPlacedOrders(){
        List<Order> orderList = orderRepository.findAllByStatusIn(List.of(OrderStatus.Placed,OrderStatus.Shipped,OrderStatus.Delivered));

        return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
    }
}
