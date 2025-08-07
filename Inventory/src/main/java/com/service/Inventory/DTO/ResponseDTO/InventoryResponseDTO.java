package com.service.Inventory.DTO.ResponseDTO;

import com.service.Inventory.Enums.InventoryStatus;

import java.time.LocalDateTime;

public record InventoryResponseDTO(
        Long id,
        String name,
        String description,
        Integer quantity,
        Double price,
        InventoryStatus status,
        LocalDateTime createdAt
) {}
