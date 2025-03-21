package com.service.Products.Service.impl;

import com.service.Products.Repositories.SubCategoryRepository;
import com.service.Products.Service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;
}
