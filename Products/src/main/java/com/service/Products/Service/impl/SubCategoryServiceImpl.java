package com.service.Products.Service.impl;

import com.service.Products.DTO.RequestDTO.MainCategoryRequest;
import com.service.Products.DTO.RequestDTO.SubcategoryRequest;
import com.service.Products.DTO.ResponseDTO.MainCategoryResponse;
import com.service.Products.Repositories.SubCategoryRepository;
import com.service.Products.Service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public List<MainCategoryResponse> getSubCategoryList() {
        return List.of();
    }

    @Override
    public boolean existSubCategoryByName(String subCategoryName) {
        return false;
    }

    @Override
    public void saveSubCategory(SubcategoryRequest subcategoryRequest) {

    }

    @Override
    public boolean existSubCategoryByNameAndIdNot(String subCategoryName, Long id) {
        return false;
    }

    @Override
    public void updateSubCategory(SubcategoryRequest subcategoryRequest) {

    }

    @Override
    public boolean existSubCategoryById(Long id) {
        return false;
    }

    @Override
    public void deleteSubCategory(Long id) {

    }

    @Override
    public MainCategoryResponse getSubCategoryById(Long id) {
        return null;
    }
}
