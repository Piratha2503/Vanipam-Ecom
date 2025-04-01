package com.service.Products.Service;

import com.service.Products.DTO.RequestDTO.MainCategoryRequest;
import com.service.Products.DTO.ResponseDTO.MainCategoryResponse;

import java.util.List;

public interface MainCategoryService {
    List<MainCategoryResponse> getMainCategoryList();

    boolean existMainCategoryByName(String mainCategoryName);

    void saveMainCategory(MainCategoryRequest mainCategoryRequest);

    boolean existMainCategoryByNameAndIdNot(String mainCategoryName, Long id);

    void updateMainCategory(MainCategoryRequest mainCategoryRequest);

    boolean existMainCategoryById(Long id);

    void deleteMainCategory(Long id);

    MainCategoryResponse getMainCategoryById(Long id);
}
