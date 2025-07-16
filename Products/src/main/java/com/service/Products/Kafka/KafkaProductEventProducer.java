package com.service.Products.Kafka;

import com.service.Products.DTO.RequestDTO.ProductRequest;

public interface KafkaProductEventProducer {
    void sendProductEvent(ProductRequest productRequest);
}
