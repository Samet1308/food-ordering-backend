package com.ecommerce.foodordering.dtos.requests.category;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CategoryDTO {

    private Long id;

    private String name;

    private String description;

    private MultipartFile img;

    private byte[] returnedImg;


}
