package com.service.Products.DTO.RequestDTO;

import lombok.Data;

@Data
public class SubcategoryRequest {
    private Long id;
    private String sub_categoryName;
    private Long mainCategory_id;
}
