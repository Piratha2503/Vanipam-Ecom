package com.service.Products.Controller;

import com.service.Products.Service.ProductService;
import com.service.Products.Utils.APIEndPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(APIEndPoints.baseAPI)
public class ProductController {
    @Autowired
    private ProductService productService;
}
