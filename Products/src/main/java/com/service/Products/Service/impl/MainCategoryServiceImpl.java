package com.service.Products.Service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.Products.DTO.RequestDTO.MainCategoryRequest;
import com.service.Products.DTO.ResponseDTO.MainCategoryResponse;
import com.service.Products.Entities.MainCategory;
import com.service.Products.Repositories.MainCategoryRepository;
import com.service.Products.Service.MainCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MainCategoryServiceImpl implements MainCategoryService {

    @Autowired
    private MainCategoryRepository mainCategoryRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<MainCategoryResponse> getMainCategoryList() {
        return mainCategoryRepository.findAll().stream().map(mainCategory ->
                MainCategoryResponse.builder().mainCategoryName(mainCategory.getMainCategoryName()).id(mainCategory.getId()).build()).toList();
    }

    @Override
    public MainCategoryResponse getMainCategoryById(Long id) {
        MainCategory mainCategory = mainCategoryRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("MainCategory not found with id: " + id));
        return MainCategoryResponse.builder().mainCategoryName(mainCategory.getMainCategoryName())
                .id(mainCategory.getId()).build();
    }

    @Override
    public boolean existMainCategoryByName(String mainCategoryName) {
        return mainCategoryRepository.existsByMainCategoryNameIgnoreCase(mainCategoryName);
    }

    @Override
    public void saveMainCategory(MainCategoryRequest mainCategoryRequest) {
        mainCategoryRepository.save(copyValuesFromRequest(mainCategoryRequest,new MainCategory()));
    }

    @Override
    public boolean existMainCategoryByNameAndIdNot(String mainCategoryName, Long id) {
        return mainCategoryRepository.existsByMainCategoryNameIgnoreCaseAndIdNot(mainCategoryName,id);
    }

    @Override
    public void updateMainCategory(MainCategoryRequest mainCategoryRequest) {
        MainCategory mainCategory = mainCategoryRepository.findById(mainCategoryRequest.getId()).orElseThrow(
                () -> new NoSuchElementException("MainCategory not found with id: " + mainCategoryRequest.getId())
        );
        mainCategoryRepository.save(copyValuesFromRequest(mainCategoryRequest,mainCategory));
    }

    @Override
    public boolean existMainCategoryById(Long id) {
        return mainCategoryRepository.existsById(id);
    }

    @Override
    public void deleteMainCategory(Long id) {
        mainCategoryRepository.deleteById(id);
    }


    private MainCategory copyValuesFromRequest(MainCategoryRequest mainCategoryRequest, MainCategory mainCategory){
        BeanUtils.copyProperties(mainCategoryRequest,mainCategory);
        return mainCategory;
    }
}
