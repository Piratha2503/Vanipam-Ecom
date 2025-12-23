package com.service.Users.Kafka;

import com.service.Users.DTO.RequestDTO.BuyerSaveDTO;

public interface KafkaProductEventProducer {
    void sendProductEvent(BuyerSaveDTO productRequest);
}
