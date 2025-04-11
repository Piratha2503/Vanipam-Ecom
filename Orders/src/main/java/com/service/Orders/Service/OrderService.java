package com.service.Orders.Service;

import com.service.Orders.DTO.ResponseDTO.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse getOrderedProducts(String orderId);
}
