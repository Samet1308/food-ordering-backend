package com.ecommerce.foodordering.dtos.requests.user;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String name;

    private String email;

    private String password;
}
