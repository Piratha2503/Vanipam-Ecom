package com.service.Products.DTO.RequestDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandRequest {
    private Long id;
    private String brandName;
    private String taxPercentage;
}
