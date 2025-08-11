package com.service.Products.DTO.RequestDTO;

import jakarta.validation.constraints.NotNull;

public record SubCategoryRequest(Long id, @NotNull String subCategoryName, @NotNull Long mainCategoryId) {}
