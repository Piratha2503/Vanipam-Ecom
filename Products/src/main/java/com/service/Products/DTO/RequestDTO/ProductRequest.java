package com.service.Products.DTO.RequestDTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductRequest {

    private Long id;
    private String productName;
    private String productType;
    private String productDescription;
    private Long brandId;
    private String unitOfMeasure;
    private Long quantity;
    private BigDecimal pricePerUnit;
    private Long subCategory_id;
    private LocalDateTime expiryDate;
}
