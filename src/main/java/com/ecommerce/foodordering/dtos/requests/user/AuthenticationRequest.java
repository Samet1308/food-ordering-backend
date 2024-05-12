package com.ecommerce.foodordering.dtos.requests.user;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;

    private String password;
}
