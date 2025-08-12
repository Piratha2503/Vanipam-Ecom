package com.service.Products.DTO.ResponseDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public record ProductResponseDTO(
        Long id,
        String productName,
        String productDescription,
        String unitOfMeasure,
        LocalDate expiryDate,
        Timestamp createdTimestamp,
        Timestamp updatedTimestamp,
        ProductTypeResponse productType,
        BrandResponse brand,
        SubCategoryResponse subCategory
) {}

