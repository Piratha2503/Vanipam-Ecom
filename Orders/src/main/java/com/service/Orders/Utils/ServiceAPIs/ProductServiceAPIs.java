package com.service.Orders.Utils.ServiceAPIs;

import lombok.Data;

@Data
public class ProductServiceAPIs {

    public static final String localServer = "http://localhost:";
    public static final String vpsServer = "https://backend.graycorp.io:";
    public static final String id = "/{id}";

    //product service baseAPI
    public static final String productPort = "9504";
    public static final String productBaseAPI = localServer+productPort+"/ecom_app/api/products/";

    //product service endpoints
    public static final String getProductById = productBaseAPI+"getProductById"+id;

}
