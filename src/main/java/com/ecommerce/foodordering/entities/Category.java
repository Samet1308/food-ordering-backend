package com.ecommerce.foodordering.entities;



import com.ecommerce.foodordering.dtos.CategoryDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Lob
    @Column(name = "img",columnDefinition = "longblob")
    private byte[] img;

    public CategoryDTO getCategoryDto(){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(id);
        categoryDTO.setName(name);
        categoryDTO.setDescription(description);
        categoryDTO.setReturnedImg(img);
        return categoryDTO;

    }
}
