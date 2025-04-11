package com.service.Orders.Service.Impl;

import com.service.Orders.DTO.ResponseDTO.OrderResponse;
import com.service.Orders.DTO.ResponseDTO.ProductResponse;
import com.service.Orders.Repositories.OrderRepository;
import com.service.Orders.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private WebClient webClient;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderResponse getOrderedProducts(String orderId) {
        String uri = "http://localhost:9501/ecom-app/api/products/getProductList";

        OrderResponse orderResponse = new OrderResponse();
        List<ProductResponse> productResponseList =  webClient.get().uri(uri)
                .retrieve().bodyToFlux(ProductResponse.class).collectList().block();

        orderResponse.setProductResponsesList(productResponseList);

        return orderResponse;
    }
}
