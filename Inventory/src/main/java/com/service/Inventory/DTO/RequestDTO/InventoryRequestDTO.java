package com.service.Inventory.DTO.RequestDTO;

import com.service.Inventory.Enums.InventoryStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record InventoryRequestDTO(
        @NotNull @Positive Integer quantity,
        @NotNull @DecimalMin(value = "0.0", inclusive = false) BigDecimal pricePerUnit,
        @NotNull Long sellerId,
        @NotNull Long productId,
        @NotNull InventoryStatus status
) {}
