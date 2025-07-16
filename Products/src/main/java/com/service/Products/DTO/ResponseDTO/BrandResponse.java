package com.service.Products.DTO.ResponseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandResponse {
    private Long id;
    private String brandName;
    private String taxPercentage;
}
