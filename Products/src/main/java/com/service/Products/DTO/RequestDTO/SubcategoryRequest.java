package com.service.Products.DTO.RequestDTO;

import lombok.Data;

@Data
public class SubcategoryRequest {
    private Long id;
    private String subCategoryName;
    private Long mainCategory_id;
}
