package com.service.Products.Utils;

import lombok.Data;

@Data
public final class APIEndPoints {
    public static final String baseAPI = "/api/v1/";
    public static final String id = "/{id}";

    // Main Category
    public static final String mainCategory = "mainCategory";
    public static final String mainCategories = "mainCategories";
    public static final String mainCategoryById = mainCategory+id;

    // Main Category
    public static final String subCategory = "subCategory";
    public static final String subCategories = "subCategories";
    public static final String subCategoryByID = subCategory+id;

    // Products
    public static final String product   = "product";
    public static final String products = "products";
    public static final String productById = product+id;

}
