package com.service.Users.Kafka.Impl;

import com.service.Users.DTO.RequestDTO.BuyerSaveDTO;
import com.service.Users.Kafka.KafkaProductEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProductEventProducerImpl implements KafkaProductEventProducer {

    @Autowired
    private KafkaTemplate<String, BuyerSaveDTO> kafkaTemplate;

    @Override
    public void sendProductEvent(BuyerSaveDTO buyerSaveDTO){
        kafkaTemplate.send("product-topic",buyerSaveDTO);
    }
}
