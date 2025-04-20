package com.service.Products.DTO.ResponseDTO;

import lombok.Data;

@Data
public class SubcategoryResponse {
    private Long id;
    private String subCategoryName;
    private MainCategoryResponse mainCategoryResponse;
}
