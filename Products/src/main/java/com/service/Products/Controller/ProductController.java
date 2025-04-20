package com.service.Products.Controller;

import com.service.Products.DTO.RequestDTO.ProductRequest;
import com.service.Products.DTO.ResponseDTO.ProductResponse;
import com.service.Products.Service.ProductService;
import com.service.Products.Utils.APIEndPoints;
import com.service.Products.Utils.ValidationCodesAndMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(APIEndPoints.baseAPI)
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ValidationCodesAndMessages validationCodesAndMessages;

    @GetMapping(APIEndPoints.getProductList)
    public ResponseEntity<Object> getProductList(){

        Map<String, List<ProductResponse>> stringListMap = new HashMap<>();
        stringListMap.put("getProductList",productService.getProductList());

        return ResponseEntity.ok().body(Map.of("data",stringListMap));
    }

    @PostMapping(APIEndPoints.saveProduct)
    public ResponseEntity<Object> saveProduct(@RequestBody ProductRequest productRequest){
        productService.saveProduct(productRequest);
        return ResponseEntity.ok().body("Product details saved");
    }
}
