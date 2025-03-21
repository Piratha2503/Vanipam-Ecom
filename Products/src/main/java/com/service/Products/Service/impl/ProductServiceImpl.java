package com.service.Products.Service.impl;

import com.service.Products.Repositories.ProductRepository;
import com.service.Products.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

}
