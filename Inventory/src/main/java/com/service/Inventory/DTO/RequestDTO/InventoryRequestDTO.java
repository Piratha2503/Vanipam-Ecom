package com.service.Inventory.DTO.RequestDTO;

import com.service.Inventory.Enums.InventoryStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InventoryRequestDTO(
        @NotBlank(message = "Name is required") String name,
        String description,
        @NotNull Integer quantity,
        @NotNull Double price,
        @NotNull(message = "Status must not be null")
        InventoryStatus status
) {}
