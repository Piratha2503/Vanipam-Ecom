package com.service.Products.DTO.RequestDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductUpdateDTO(
        @NotNull(message = "Product ID is required")
        Long id,

        String productName,

        String productType,

        @Size(max = 500, message = "Product description must be less than 500 characters")
        String productDescription,

        Long brandId,

        String unitOfMeasure,

        @Positive(message = "Quantity must be positive")
        Long quantity,

        @Positive(message = "Price per unit must be positive")
        BigDecimal pricePerUnit,

        Long subCategoryId,

        LocalDate expiryDate
) {}
