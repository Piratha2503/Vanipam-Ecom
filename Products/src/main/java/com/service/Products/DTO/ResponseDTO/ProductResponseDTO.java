package com.service.Products.DTO.ResponseDTO;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductResponseDTO(
        Long id,
        String productName,
        String productType,
        String productDescription,
        Long brandId,
        String unitOfMeasure,
        Long quantity,
        BigDecimal pricePerUnit,
        Long subCategoryId,
        Timestamp createdTimestamp,
        Timestamp updatedTimestamp,
        LocalDate expiryDate
) {}

