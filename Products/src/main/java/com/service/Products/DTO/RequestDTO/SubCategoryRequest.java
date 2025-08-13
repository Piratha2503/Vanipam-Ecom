package com.service.Products.DTO.RequestDTO;

import jakarta.validation.constraints.NotNull;

public record SubCategoryRequest(Long id, @NotNull String subCategoryName, @NotNull(message = "mainCategoryId cannot be null") Long mainCategoryId) {}
