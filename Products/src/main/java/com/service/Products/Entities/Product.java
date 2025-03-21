package com.service.Products.Entities;

import com.service.Products.Utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends DateTimeUtils {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String productType;
    private String productDescription;
    private String brand;
    private String quantityType;
    private Long quantity;
    private String price;
    @ManyToOne
    @JoinColumn(name = "sub_categoryId")
    private SubCategory subCategory;
}
