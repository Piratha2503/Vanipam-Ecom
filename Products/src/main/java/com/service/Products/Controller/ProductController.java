package com.service.Products.Controller;

import com.service.Products.APIResponse.APIContentResponse;
import com.service.Products.Enums.ResponseStatus;
import com.service.Products.Service.ProductService;
import com.service.Products.Utils.APIEndPoints;
import com.service.Products.Utils.ValidationCodesAndMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return ResponseEntity.ok().body(new APIContentResponse<>(
                ResponseStatus.SUCCESS.getStatus(),
                validationCodesAndMessages.getCommonSuccessCode(),
                validationCodesAndMessages.getGetSuccessMessage(),
                "productList",
                productService.getProductList()

        ));
    }
}
