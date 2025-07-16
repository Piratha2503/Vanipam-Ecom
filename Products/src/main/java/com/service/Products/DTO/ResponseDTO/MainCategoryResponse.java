package com.service.Products.DTO.ResponseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MainCategoryResponse {
    private Long id;
    private String mainCategoryName;
}
