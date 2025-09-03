package com.service.Users.Kafka;

import com.service.Products.DTO.RequestDTO.ProductSaveDTO;

public interface KafkaProductEventProducer {
    void sendProductEvent(ProductSaveDTO productRequest);
}
