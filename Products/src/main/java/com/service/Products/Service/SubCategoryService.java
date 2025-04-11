package com.service.Products.Service;

import com.service.Products.DTO.RequestDTO.MainCategoryRequest;
import com.service.Products.DTO.RequestDTO.SubcategoryRequest;
import com.service.Products.DTO.ResponseDTO.MainCategoryResponse;

import java.util.List;

public interface SubCategoryService {
    List<MainCategoryResponse> getSubCategoryList();

    boolean existSubCategoryByName(String subCategoryName);

    void saveSubCategory(SubcategoryRequest subcategoryRequest);

    boolean existSubCategoryByNameAndIdNot(String mainCategoryName, Long id);

    void updateSubCategory(SubcategoryRequest subcategoryRequest);

    boolean existSubCategoryById(Long id);

    void deleteSubCategory(Long id);

    MainCategoryResponse getSubCategoryById(Long id);
}
