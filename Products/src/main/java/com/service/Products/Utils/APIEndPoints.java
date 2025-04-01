package com.service.Products.Utils;

import lombok.Data;

@Data
public final class APIEndPoints {
    public static final String baseAPI = "/api/products/";
    public static final String id = "/{id}";

    // Main Category
    public static final String saveMainCategory = "saveMainCategory";
    public static final String getMainCategoryList = "getMainCategoryList";
    public static final String updateMainCategory = "updateMainCategory";
    public static final String deleteMainCategory = "deleteMainCategory";
    public static final String getMainCategoryByID = "getMainCategoryByID"+id;

    // Products
    public static final String saveProduct   = "saveProduct";
    public static final String getProductList= "getProductList";
    public static final String updateProduct= "updateProduct";
    public static final String deleteProduct= "deleteProduct";
    public static final String getProductById = "getProduct"+id;
}
