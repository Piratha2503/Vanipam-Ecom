package com.service.Products.Entities;

import com.service.Products.Utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Brand extends DateTimeUtils {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brandName;
    private String taxPercentage;
    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
