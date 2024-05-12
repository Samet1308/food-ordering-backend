package com.ecommerce.foodordering.services.abstracts;

import com.ecommerce.foodordering.dtos.requests.user.CreateUserRequest;
import com.ecommerce.foodordering.dtos.responses.user.GetAllUserResponse;

public interface AuthService {
    GetAllUserResponse createUser(CreateUserRequest createUserRequest);
}
