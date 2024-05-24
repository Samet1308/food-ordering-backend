package com.ecommerce.foodordering.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
