package com.ecommerce.foodordering.dtos;

import com.ecommerce.foodordering.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserResponse {
    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRole userRole;
}
