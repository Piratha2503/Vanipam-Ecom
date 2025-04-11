package com.service.Orders.DTO.ResponseDTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private String orderNumber;
    private List<ProductResponse> productResponsesList;
}
