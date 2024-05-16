package com.ecommerce.foodordering.dtos.responses.product;

import lombok.Data;

@Data
public class AddProductInCartDto {

    private Long userId;

    private Long productId;
}
