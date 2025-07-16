package com.service.Products.Service.impl;

import com.service.Products.DTO.RequestDTO.SubcategoryRequest;
import com.service.Products.DTO.ResponseDTO.MainCategoryResponse;
import com.service.Products.DTO.ResponseDTO.SubcategoryResponse;
import com.service.Products.Entities.MainCategory;
import com.service.Products.Entities.SubCategory;
import com.service.Products.Repositories.SubCategoryRepository;
import com.service.Products.Service.MainCategoryService;
import com.service.Products.Service.SubCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private MainCategoryService mainCategoryService;

    @Override
    public List<SubcategoryResponse> getSubCategoryList() {
        return subCategoryRepository.findAll().stream().map(this::copyToSubcategoryResponse).toList();
    }

    @Override
    public boolean existSubCategoryByName(String subCategoryName) {
        return false;
    }

    @Override
    public void saveSubCategory(SubcategoryRequest subcategoryRequest) {
        SubCategory subCategory = new SubCategory();
        MainCategoryResponse mainCategoryResponse = mainCategoryService.getMainCategoryById(subcategoryRequest.getMainCategory_id());
        MainCategory mainCategory = new MainCategory();
        BeanUtils.copyProperties(mainCategoryResponse,mainCategory);
        subCategory.setMainCategory(mainCategory);
        BeanUtils.copyProperties(subcategoryRequest,subCategory);
        subCategoryRepository.save(subCategory);
    }

    @Override
    public boolean existSubCategoryByNameAndIdNot(String subCategoryName, Long id) {
        return false;
    }

    @Override
    public void updateSubCategory(SubcategoryRequest subcategoryRequest) {
        SubCategory subCategory = subCategoryRepository.getReferenceById(subcategoryRequest.getId());
        BeanUtils.copyProperties(subcategoryRequest,subCategory);
        MainCategoryResponse mainCategoryResponse = mainCategoryService.getMainCategoryById(subcategoryRequest.getMainCategory_id());
        MainCategory mainCategory = new MainCategory();
        BeanUtils.copyProperties(mainCategoryResponse,mainCategory);
        subCategory.setMainCategory(mainCategory);
        BeanUtils.copyProperties(subcategoryRequest,subCategory);
        subCategoryRepository.save(subCategory);
    }

    @Override
    public boolean existSubCategoryById(Long id) {
        return subCategoryRepository.existsById(id);
    }

    @Override
    public void deleteSubCategory(Long id) {

    }

    @Override
    public SubcategoryResponse getSubCategoryById(Long id) {
        return copyToSubcategoryResponse(subCategoryRepository.getReferenceById(id));
    }

    public SubcategoryResponse copyToSubcategoryResponse(SubCategory subCategory){
        return SubcategoryResponse.builder()
                .id(subCategory.getId())
                .subCategoryName(subCategory.getSubCategoryName())
                .mainCategoryResponse(MainCategoryResponse.builder()
                        .id(subCategory.getMainCategory().getId())
                        .mainCategoryName(subCategory.getMainCategory().getMainCategoryName())
                        .build())
                .build();
    }
}
