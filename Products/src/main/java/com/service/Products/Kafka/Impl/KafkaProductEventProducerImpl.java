package com.service.Products.Kafka.Impl;

import com.service.Products.DTO.RequestDTO.ProductSaveDTO;
import com.service.Products.Kafka.KafkaProductEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProductEventProducerImpl implements KafkaProductEventProducer {

    @Autowired
    private KafkaTemplate<String, ProductSaveDTO> kafkaTemplate;

    @Override
    public void sendProductEvent(ProductSaveDTO productRequest){
        kafkaTemplate.send("product-topic",productRequest);
    }
}
