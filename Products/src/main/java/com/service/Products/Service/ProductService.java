package com.service.Products.Service;

import com.service.Products.DTO.RequestDTO.ProductRequest;
import com.service.Products.DTO.ResponseDTO.ProductResponse;
import com.service.Products.Entities.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getProductList();

    void saveProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long id);


}
