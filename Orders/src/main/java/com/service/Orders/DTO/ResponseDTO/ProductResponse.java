package com.service.Orders.DTO.ResponseDTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductResponse {
    private Long id;
    private String productName;
    private String productType;
    private String productDescription;
    private String brand;
    private String unitOfMeasure;
    private Long quantity;
    private BigDecimal pricePerUnit;
    private SubcategoryResponse subcategoryResponse;
    private LocalDateTime expiryDate;
}
