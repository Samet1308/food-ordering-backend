package com.ecommerce.foodordering.entities;


import com.ecommerce.foodordering.dtos.requests.category.CategoryDTO;
import com.ecommerce.foodordering.dtos.requests.product.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long price;

    @Lob
    @Column(name = "img",columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public ProductDTO getProductDto(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);
        productDTO.setName(name);
        productDTO.setPrice(price);
        productDTO.setDescription(description);
        productDTO.setReturnedImg(img);
        productDTO.setCategoryId(category.getId());
        productDTO.setCategoryName(category.getName());
        return productDTO;

    }
}
