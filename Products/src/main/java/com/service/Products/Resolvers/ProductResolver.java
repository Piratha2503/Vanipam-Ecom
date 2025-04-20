package com.service.Products.Resolvers;

import com.service.Products.DTO.ResponseDTO.ProductResponse;
import com.service.Products.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductResolver {

    @Autowired
    private ProductService productService;

    @QueryMapping
    public List<ProductResponse> getProductList(){
        return productService.getProductList();
    }

    @QueryMapping
    public ProductResponse getProductById(@Argument Long id){
        return productService.getProductById(id);
    }
}
