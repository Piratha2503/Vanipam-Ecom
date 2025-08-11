package com.service.Products.DTO.RequestDTO;

import jakarta.validation.constraints.NotNull;

public record MainCategoryRequest(Long id, @NotNull String mainCategoryName) {}
