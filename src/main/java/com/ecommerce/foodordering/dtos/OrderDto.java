package com.ecommerce.foodordering.dtos;

import com.ecommerce.foodordering.dtos.CartItemsDto;
import com.ecommerce.foodordering.entities.CartItems;
import com.ecommerce.foodordering.entities.User;
import com.ecommerce.foodordering.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {

    private Long id;

    private String orderDescription;

    private Date date;

    private Long amount;

    private String address;

    private String payment;

    private OrderStatus status;

    private Long totalAmount;

    private Long discount;

    private UUID trackingId;

    private String  userName;

    private List<CartItemsDto> cartItems;
}
