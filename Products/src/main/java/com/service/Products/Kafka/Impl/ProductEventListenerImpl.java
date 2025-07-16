package com.service.Products.Kafka.Impl;

import com.service.Products.DTO.RequestDTO.ProductRequest;
import com.service.Products.Kafka.ProductEventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListenerImpl implements ProductEventListener {

    @KafkaListener(topics = "product-topic", groupId = "product-group")
    @Override
    public void listenProductEvent(ProductRequest productRequest){
        printMsg(productRequest);
    }

    void printMsg(ProductRequest productRequest){
        System.out.println(productRequest);
    }

}
