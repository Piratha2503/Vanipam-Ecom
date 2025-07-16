package com.service.Products.Kafka;

import com.service.Products.DTO.RequestDTO.ProductRequest;

public interface ProductEventListener {
    void listenProductEvent(ProductRequest productRequest);
}
