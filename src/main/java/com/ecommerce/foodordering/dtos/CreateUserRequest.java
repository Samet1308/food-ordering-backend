package com.ecommerce.foodordering.dtos;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String name;

    private String email;

    private String password;
}
