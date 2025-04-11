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

    // Main Category
    public static final String saveSubCategory = "saveSubCategory";
    public static final String getSubCategoryList = "getSubCategoryList";
    public static final String updateSubCategory = "updateSubCategory";
    public static final String deleteSubCategory = "deleteSubCategory";
    public static final String getSubCategoryByID = "getSubCategoryByID"+id;

    // Products
    public static final String saveProduct   = "saveProduct";
    public static final String getProductList= "getProductList";
    public static final String updateProduct= "updateProduct";
    public static final String deleteProduct= "deleteProduct";
    public static final String getProductById = "getProduct"+id;
}
