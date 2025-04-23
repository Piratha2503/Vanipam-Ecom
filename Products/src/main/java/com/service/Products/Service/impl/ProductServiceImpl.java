package com.service.Products.Service.impl;

import com.service.Products.DTO.RequestDTO.ProductRequest;
import com.service.Products.DTO.ResponseDTO.MainCategoryResponse;
import com.service.Products.DTO.ResponseDTO.ProductResponse;
import com.service.Products.DTO.ResponseDTO.SubcategoryResponse;
import com.service.Products.Entities.Product;
import com.service.Products.Entities.SubCategory;
import com.service.Products.Repositories.ProductRepository;
import com.service.Products.Repositories.SubCategoryRepository;
import com.service.Products.Service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public ProductResponse getProductById(Long id){

        return copyValuesToResponse(productRepository.getReferenceById(id));

    }

    @Override
    public List<ProductResponse> getProductList() {
        return productRepository.findAll().stream().map(this::copyValuesToResponse).toList();
    }

    @Override
    public void saveProduct(ProductRequest productRequest) {
            Product product = new Product();
            BeanUtils.copyProperties(productRequest, product);
            SubCategory subCategory = subCategoryRepository.getReferenceById(productRequest.getSubCategory_id());
            product.setSubCategory(subCategory);
            productRepository.save(product);

    }

    public ProductResponse copyValuesToResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product,productResponse);

        SubcategoryResponse subcategoryResponse = new SubcategoryResponse();
        MainCategoryResponse mainCategoryResponse = new MainCategoryResponse();

        BeanUtils.copyProperties(product.getSubCategory().getMainCategory(),mainCategoryResponse);
        BeanUtils.copyProperties(product.getSubCategory(),subcategoryResponse);
        subcategoryResponse.setMainCategoryResponse(mainCategoryResponse);
        productResponse.setSubCategoryResponse(subcategoryResponse);

        return productResponse;
    }
}
