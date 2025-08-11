package com.service.Products.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductSaveDTO(
        @NotBlank(message = "Product name is required")
        String productName,

        @NotBlank(message = "Product type is required")
        String productType,

        @Size(max = 500, message = "Product description must be less than 500 characters")
        String productDescription,

        @NotNull(message = "Brand ID is required")
        Long brandId,

        @NotBlank(message = "Unit of measure is required")
        String unitOfMeasure,

        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity must be positive")
        Long quantity,

        @NotNull(message = "Price per unit is required")
        @Positive(message = "Price per unit must be positive")
        BigDecimal pricePerUnit,

        @NotNull(message = "Subcategory ID is required")
        Long subCategoryId,

        LocalDate expiryDate
) {}
