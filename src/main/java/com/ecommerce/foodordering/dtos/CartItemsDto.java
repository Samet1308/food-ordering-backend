package com.ecommerce.foodordering.dtos;

import com.ecommerce.foodordering.entities.Order;
import com.ecommerce.foodordering.entities.Product;
import com.ecommerce.foodordering.entities.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
public class CartItemsDto {

    private Long id;

    private Long price;

    private Long quantity;

    private Long productId;

    private Long userId;

    private Long orderId;

    private String productName;

    private byte[] returnedImg;

}
