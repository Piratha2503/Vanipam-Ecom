package com.service.Products.Entities;

import com.service.Products.Utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Product extends DateTimeUtils {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    private String productType;

    private String productDescription;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "unit_of_measure")
    private String unitOfMeasure;

    private Long quantity;

    @Column(name = "price_per_unit", nullable = false)
    private BigDecimal pricePerUnit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

}