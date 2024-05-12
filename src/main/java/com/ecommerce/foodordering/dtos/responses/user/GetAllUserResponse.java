package com.ecommerce.foodordering.dtos.responses.user;

import com.ecommerce.foodordering.enums.UserRole;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllUserResponse {

    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRole userRole;

}
