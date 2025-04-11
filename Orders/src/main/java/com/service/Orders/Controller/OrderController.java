package com.service.Orders.Controller;

import com.service.Orders.Service.OrderService;
import com.service.Orders.Utils.APIEndPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(APIEndPoints.baseAPI)
public class OrderController {

    @Autowired
    private OrderService orderService;

    public ResponseEntity<Object> getOrderedProducts(@PathVariable(value = "orderId") String orderId){

        return ResponseEntity.ok().body(orderService.getOrderedProducts(orderId));
    }

}
