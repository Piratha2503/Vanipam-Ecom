package com.service.Products.DTO.RequestDTO;

import jakarta.validation.constraints.NotNull;

public record ProductTypeRequest(Long id, @NotNull String name) {}
