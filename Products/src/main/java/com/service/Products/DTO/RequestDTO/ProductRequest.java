package com.service.Products.DTO.RequestDTO;

import com.service.Products.Entities.SubCategory;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductRequest {

    private Long id;
    private String productName;
    private String productType;
    private String productDescription;
    private String brand;
    private String unitOfMeasure;
    private Long quantity;
    private BigDecimal pricePerUnit;
    private Long subCategory_id;
    private LocalDateTime expiryDate;
}
