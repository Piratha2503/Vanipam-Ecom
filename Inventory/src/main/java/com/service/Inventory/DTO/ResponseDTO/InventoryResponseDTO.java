package com.service.Inventory.DTO.ResponseDTO;

import com.service.Inventory.Enums.InventoryStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InventoryResponseDTO(
       Integer quantity,
       BigDecimal pricePerUnit,
       Long sellerId,
       Long productId,
       InventoryStatus status) {}
