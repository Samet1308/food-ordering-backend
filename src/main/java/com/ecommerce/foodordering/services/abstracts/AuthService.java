package com.ecommerce.foodordering.services.abstracts;

import com.ecommerce.foodordering.dtos.CreateUserRequest;
import com.ecommerce.foodordering.dtos.GetAllUserResponse;

public interface AuthService {
    GetAllUserResponse createUser(CreateUserRequest createUserRequest);
}
