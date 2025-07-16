package com.service.Products.Kafka.Impl;

import com.service.Products.DTO.RequestDTO.ProductRequest;
import com.service.Products.Kafka.KafkaProductEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProductEventProducerImpl implements KafkaProductEventProducer {

    @Autowired
    private KafkaTemplate<String,ProductRequest> kafkaTemplate;

    @Override
    public void sendProductEvent(ProductRequest productRequest){
        kafkaTemplate.send("product-topic",productRequest);
    }
}
